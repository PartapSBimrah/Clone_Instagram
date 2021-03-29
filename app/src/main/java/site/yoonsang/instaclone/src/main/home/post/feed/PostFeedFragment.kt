package site.yoonsang.instaclone.src.main.home.post.feed

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import site.yoonsang.instaclone.R
import site.yoonsang.instaclone.config.BaseFragment
import site.yoonsang.instaclone.databinding.FragmentPostFeedBinding
import site.yoonsang.instaclone.src.main.home.post.feed.photosEdit.PhotosEditActivity
import site.yoonsang.instaclone.src.main.home.post.feed.photosEdit.photoEdit.PhotoEditActivity
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class PostFeedFragment : BaseFragment<FragmentPostFeedBinding>(
    FragmentPostFeedBinding::bind,
    R.layout.fragment_post_feed
) {
    private lateinit var postFeedAdapter: PostFeedAdapter

    private val REQUEST_IMAGE_CAPTURE = 1
    private lateinit var currentPhotoPath: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getAllPhotos()

        binding.postFeedBtnNext.setOnClickListener {
            if (postFeedAdapter.multipleArray.size <= 1) {
                val intent = Intent(activity, PhotoEditActivity::class.java)
                if (postFeedAdapter.multipleArray.size == 1) {
                    intent.putExtra("photo", postFeedAdapter.multipleArray[0])
                } else {
                    intent.putExtra("photo", postFeedAdapter.defaultUrl)
                }
                startActivity(intent)
            } else {
                val intent = Intent(activity, PhotosEditActivity::class.java)
                intent.putExtra("photos", postFeedAdapter.multipleArray)
                startActivity(intent)
            }
        }

        binding.postFeedCamera.setOnClickListener {
            startCapture()
        }

        binding.postFeedMultiplePhotos.setOnClickListener {
            if (!postFeedAdapter.multiple) {
                binding.postFeedMultiplePhotos.setBackgroundResource(R.drawable.feed_selected_button_background)
                postFeedAdapter.multiple = true
            } else {
                binding.postFeedMultiplePhotos.setBackgroundResource(R.drawable.feed_unselected_button_background)
                postFeedAdapter.multiple = false
            }
            postFeedAdapter.notifyDataSetChanged()
        }
    }

    private fun getAllPhotos() {
        val cursor: Cursor?
        if (Build.VERSION.SDK_INT >= 29) {
            cursor = activity!!.contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC"
            )
        } else {
            cursor = activity!!.contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                null
            )
        }
        val uriArr = ArrayList<String>()
        if (cursor != null) {
            while (cursor.moveToNext()) {
                // 사진 경로 Uri 가져오기
                val uri =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                uriArr.add(uri)
            }
            cursor.close()
        }

        Glide.with(context!!)
            .load(uriArr[0])
            .into(binding.postFeedImageView)

        postFeedAdapter = PostFeedAdapter(context!!, uriArr, binding.postFeedImageView)
        binding.postFeedGalleryRecyclerView.adapter = postFeedAdapter
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = activity!!.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply {
            currentPhotoPath = absolutePath
        }
    }

    private fun startCapture() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(activity!!.packageManager)?.also {
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    null
                }
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        context!!,
                        "site.yoonsang.instaclone.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val file = File(currentPhotoPath)
            val intent = Intent(activity, PhotoEditActivity::class.java)
            intent.putExtra("photo", file.absolutePath)
            startActivity(intent)
        }
    }
}

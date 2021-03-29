package site.yoonsang.instaclone.src.main.home.post.feed.photosEdit.upload

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import site.yoonsang.instaclone.config.BaseActivity
import site.yoonsang.instaclone.databinding.ActivityUploadBinding
import site.yoonsang.instaclone.src.main.MainActivity
import site.yoonsang.instaclone.src.main.home.post.feed.photosEdit.upload.models.Media
import site.yoonsang.instaclone.src.main.home.post.feed.photosEdit.upload.models.PostFeedUploadRequest
import site.yoonsang.instaclone.src.main.home.post.feed.photosEdit.upload.models.PostFeedUploadResponse
import java.io.File
import kotlin.collections.ArrayList

class UploadActivity : BaseActivity<ActivityUploadBinding>(ActivityUploadBinding::inflate),
    UploadFeedView {

    private lateinit var fbStorage: FirebaseStorage
    private lateinit var uriPhoto: Uri
    private var uriPhotoList = arrayListOf<Uri>()
    private var urlList = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val photos = arrayListOf<String>()
        if (intent.getSerializableExtra("photos") != null) {
            for (photo in intent.getSerializableExtra("photos") as ArrayList<String>) {
                photos.add(photo)
            }
            Glide.with(this)
                .load(photos[0])
                .into(binding.uploadImage)
            binding.uploadIfPhotos.visibility = View.VISIBLE
        } else if (intent.getStringExtra("photo") != null) {
            photos.add(intent.getStringExtra("photo")!!)
            Glide.with(this)
                .load(photos[0])
                .into(binding.uploadImage)
            binding.uploadIfPhotos.visibility = View.GONE
        }

        fbStorage = FirebaseStorage.getInstance()
        for (photo in photos) {
            uriPhoto = photo.toUri()
            uriPhotoList.add(uriPhoto)
        }

        imageUploadFirebase()

        binding.uploadBtnCheck.setOnClickListener {
            showLoadingDialog(this)
            imageUploadServer()
        }
    }

    override fun onPostFeedCreateSuccess(response: PostFeedUploadResponse) {
        dismissLoadingDialog()
        if (response.isSuccess) {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        } else {
            showCustomToast(response.message.toString())
        }
    }

    override fun onPostFeedCreateFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }

    private fun imageUploadFirebase() {
        showLoadingDialog(this)
        for (uri in uriPhotoList) {
            val file = Uri.fromFile(File(uri.toString()))
            val storageRef = fbStorage.reference.child("images")
            val fileRef = storageRef.child("images/${file.lastPathSegment}")

            fileRef.putFile(file)
                .addOnSuccessListener {
                    fileRef.downloadUrl
                        .addOnCompleteListener {
                            urlList.add(it.result.toString())
                            if (urlList.size == uriPhotoList.size) {
                                dismissLoadingDialog()
                            }
                        }
                }
        }
    }

    private fun imageUploadServer() {
        val mediaList = arrayListOf<Media>()
        for (url in urlList) {
            val media = Media(url, "P")
            mediaList.add(media)
        }

        val content = binding.uploadEtContent.text.toString()
        val contentStringList = content.split(" ")

        val tagList = arrayListOf<String>()
        for (string in contentStringList) {
            if (string.startsWith("#")) {
                tagList.add(string)
            }
        }

        if (content.isNotEmpty() && tagList.size != 0) {
            val postUploadRequest = PostFeedUploadRequest(content, mediaList, tagList)
            UploadService(this).tryPostFeedCreate(postUploadRequest)
        } else {
            dismissLoadingDialog()
            showCustomToast("문구를 입력해주세요")
        }
    }
}
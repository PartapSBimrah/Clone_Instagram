package site.yoonsang.instaclone.src.main.home.post.feed.photosEdit.upload

import site.yoonsang.instaclone.src.main.home.post.feed.photosEdit.upload.models.PostFeedUploadResponse

interface UploadFeedView {

    fun onPostFeedCreateSuccess(response: PostFeedUploadResponse)

    fun onPostFeedCreateFailure(message: String)
}
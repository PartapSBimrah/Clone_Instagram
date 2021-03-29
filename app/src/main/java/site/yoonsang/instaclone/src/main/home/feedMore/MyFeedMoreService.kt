package site.yoonsang.instaclone.src.main.home.feedMore

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import site.yoonsang.instaclone.config.ApplicationClass
import site.yoonsang.instaclone.src.main.home.feedMore.models.DeleteFeedResponse

class MyFeedMoreService(val view: MyFeedMoreFragmentView) {

    fun tryPatchFeedDelete(userId: Int, feedId: Int) {
        val myFeedMoreRetrofitInterface = ApplicationClass.sRetrofit.create(MyFeedMoreRetrofitInterface::class.java)
        myFeedMoreRetrofitInterface.deleteFeed(userId, feedId).enqueue(object : Callback<DeleteFeedResponse> {
            override fun onResponse(
                call: Call<DeleteFeedResponse>,
                response: Response<DeleteFeedResponse>
            ) {
                view.onPatchDeleteFeedSuccess(response.body() as DeleteFeedResponse)
            }

            override fun onFailure(call: Call<DeleteFeedResponse>, t: Throwable) {
                view.onPatchDeleteFeedFailure(t.message ?: "통신 오류")
            }
        })
    }
}
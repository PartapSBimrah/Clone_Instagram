package site.yoonsang.instaclone.src.main.home.liker

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import site.yoonsang.instaclone.config.ApplicationClass
import site.yoonsang.instaclone.src.main.home.liker.models.FeedLikerResponse

class FeedLikerService(val view: FeedLikerFragmentView) {

    fun tryGetFeedLiker(userId: Int, feedId: Int) {
        val feedLikerRetrofitInterface = ApplicationClass.sRetrofit.create(FeedLikerRetrofitInterface::class.java)
        feedLikerRetrofitInterface.getFeedLiker(userId, feedId).enqueue(object : Callback<FeedLikerResponse> {
            override fun onResponse(
                call: Call<FeedLikerResponse>,
                response: Response<FeedLikerResponse>
            ) {
                view.onGetFeedLikerSuccess(response.body() as FeedLikerResponse)
            }

            override fun onFailure(call: Call<FeedLikerResponse>, t: Throwable) {
                view.onGetFeedLikerFailure(t.message ?: "통신 오류")
            }
        })
    }
}
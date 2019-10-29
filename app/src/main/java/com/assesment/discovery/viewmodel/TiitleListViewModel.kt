package com.assesment.discovery.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.assesment.discovery.model.UserManager
import com.assesment.discovery.model.data.UserPost
import com.assesment.discovery.model.network.DataResponse
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class TitleListViewModel : ViewModel() {

    var userPostsLiveData: MutableLiveData<DataResponse<List<UserPost>>>? = null

    /**
     * We are saving and checking 'userPostsLiveData' to avoid API call for configuration changes
     * **/
    fun getUsers(): LiveData<DataResponse<List<UserPost>>> {
        if (userPostsLiveData == null || userPostsLiveData!!.value == null) {
            userPostsLiveData = MutableLiveData()
            var disposable: Disposable? = null
            UserManager.getUsers().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<List<UserPost>> {
                    override fun onComplete() {
                        disposable!!.dispose()
                    }

                    override fun onSubscribe(d: Disposable?) {
                        userPostsLiveData!!.value = DataResponse(DataResponse.Status.LOADING)
                        disposable = d
                    }

                    override fun onError(e: Throwable?) {
                        disposable!!.dispose()
                        userPostsLiveData!!.value = DataResponse(DataResponse.Status.ERROR)
                    }

                    override fun onNext(value: List<UserPost>?) {
                        userPostsLiveData!!.value = DataResponse(value!!)
                    }

                })
        }
        return userPostsLiveData!!
    }

}
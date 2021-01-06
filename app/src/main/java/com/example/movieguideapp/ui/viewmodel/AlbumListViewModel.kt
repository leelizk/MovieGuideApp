package com.example.movieguideapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.movieguideapp.base.common.schedulers.BaseSchedulerProvider
import com.example.movieguideapp.base.extension.with
import com.example.movieguideapp.data.AlbumRepository
import com.example.movieguideapp.data.model.Album
import com.example.movieguideapp.ui.vo.AlbumItem
import com.example.movieguideapp.ui.vo.AlbumTwoItem
import io.reactivex.rxkotlin.subscribeBy


class AlbumListViewModel(application: Application,
                         private val albumRepository: AlbumRepository,
                         val navigationViewModel: AlbumListNavigationViewModel,
                         private val schedulerProvider: BaseSchedulerProvider
) : BaseViewModel(application) {

    companion object {
        /*
        * w500/original can replace
        * filetype .jpg,.png, .svg
        */
        const val BASE_IMG_W500_PREFIX = "https://image.tmdb.org/t/p/w500/";
    }


    var albums: List<Album>? = listOf();

    // why ??
    private val _albumListData: MutableLiveData<List<AlbumTwoItem>> = MutableLiveData<List<AlbumTwoItem>>();

    //动态数据
    val albumListData: MutableLiveData<List<AlbumTwoItem>> = _albumListData;

    fun onActivityCreated() {
        loadData();
    }


    //加载测试数据
    fun loadData() {
        rxLaunch {

            var params: Map<String, String> = mapOf<String, String>();
            albumRepository.getAll(true, params)
                    .with(schedulerProvider)
                    .subscribeBy(
                            onSuccess = {
                                albums = it;
                                //TODO 不能使用 postValue ?? 如果使用，不会刷新
                                _albumListData.value = convertItems()
                            },
                            onError = { _errorLiveData.value = it }
                    )

        }
    }

    private fun convertItems(): List<AlbumTwoItem> {
        var pageSize = 2;
        var list: MutableList<AlbumTwoItem> = mutableListOf<AlbumTwoItem>();
        var size: Int? = albums?.size;
        for ((index, cr) in albums?.withIndex()!!) {
            var newIndex: Int = index * pageSize;
            var nextIndex: Int = newIndex + 1;
            var itemOne: AlbumItem? = null
            if (newIndex < size!!) {
                var tmp: Album? = albums?.get(newIndex);
                itemOne = AlbumItem(newIndex, tmp?.title,
                        tmp?.poster,
                        onClick = {
                            //通过 xml 传参
                            //可以传当前的view 或者 context
                            go2Detail(tmp?.id!!)
                        }
                );
                var itemTwo: AlbumItem? = null;

                if (nextIndex < size) {
                    var tmp2: Album? = albums?.get(nextIndex);
                    itemTwo = AlbumItem(nextIndex, tmp2?.title,
                            tmp2?.poster,
                            onClick = {
                                go2Detail(tmp2?.id!!)
                            });
                }
                list.add(AlbumTwoItem(itemOne, itemTwo))
            }
        }
        return list;
    }


    private fun go2Detail(itemId: Long) {
        navigationViewModel.showDetail(itemId);
    }


}
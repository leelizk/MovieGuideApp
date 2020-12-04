package com.example.movieguideapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.movieguideapp.R
import com.example.movieguideapp.base.common.autoCleared
import com.example.movieguideapp.ui.vo.AlbumTwoItem
import com.example.movieguideapp.databinding.FragmentAlbumListBinding
import com.example.movieguideapp.ui.adapter.AlbumListAdapter
import com.example.movieguideapp.ui.viewmodel.AlbumListViewModel

class AlbumFragment : BaseFragment() {

    private val viewModel: AlbumListViewModel by viewModel()

    private lateinit var adapter: AlbumListAdapter<AlbumTwoItem>

    private var binding by autoCleared<FragmentAlbumListBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_album_list,
            container,
            false
        )
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.refresh, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) { R.id.refresh -> {
                viewModel.loadData()
                true
            }
            else -> super.onOptionsItemSelected(item)
    }
        return false;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
        initLiveData()
        initErrorHandler(viewModel)
        //加载数据
        viewModel.onActivityCreated()
    }

    private fun initView() {
        adapter = AlbumListAdapter(requireContext())
        val linearLayoutManager = LinearLayoutManager(requireContext())
        val dividerItemDecoration = DividerItemDecoration(binding.recyclerView.context, linearLayoutManager.orientation)
        binding.recyclerView.layoutManager = linearLayoutManager
        binding.recyclerView.addItemDecoration(dividerItemDecoration)
        binding.recyclerView.adapter = adapter
    }

    private fun initLiveData() {
        //监听数据变化
        viewModel.albumListData.observe(viewLifecycleOwner, Observer { list ->
            adapter.updateItems(list)
        })
    }
}

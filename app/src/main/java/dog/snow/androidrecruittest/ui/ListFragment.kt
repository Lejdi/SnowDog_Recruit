package dog.snow.androidrecruittest.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Slide
import dagger.hilt.android.AndroidEntryPoint
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.databinding.ListFragmentBinding
import dog.snow.androidrecruittest.ui.adapter.ListAdapter
import dog.snow.androidrecruittest.ui.model.ListItem
import dog.snow.androidrecruittest.ui.util.Constants
import dog.snow.androidrecruittest.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.layout_empty_view.view.*
import kotlinx.android.synthetic.main.layout_search.view.*

@AndroidEntryPoint
class ListFragment : Fragment(), ListAdapter.OnListFragmentInteractionListener{
    private val viewModel : ListViewModel by viewModels()
    private lateinit var binding: ListFragmentBinding
    private lateinit var adapter: ListAdapter

    private var selectedItemID : Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        subscribeObservers()
        initRecyclerView()
        viewModel.retrieveData()
        selectedItemID = viewModel.getFirstItemId()
    }

    private fun subscribeObservers(){
        viewModel.displayedItemsCount.observe(this, Observer {
            binding.root.tv_empty.isVisible = (it == 0)
        })

        binding.root.et_search.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.filterString.value=p0.toString()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun initRecyclerView(){
        adapter = ListAdapter(viewModel, this)
        binding.rvItems.adapter = adapter
        val layoutManager = LinearLayoutManager(activity)
        binding.rvItems.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(activity, layoutManager.orientation)
        itemDecoration.setDrawable(ContextCompat.getDrawable(requireActivity().baseContext, R.drawable.list_item_divider)!!)
        binding.rvItems.addItemDecoration(itemDecoration)
    }

    override fun onListFragmentClickInteraction(listItem: ListItem, position: Int) {
        selectedItemID = listItem.id
        if(selectedItemID!=null){
            val detailsFragment = DetailsFragment()

            detailsFragment.enterTransition = Slide(Gravity.END)
            detailsFragment.exitTransition = Slide(Gravity.END)

            val bundle = Bundle()
            bundle.putInt(Constants.KEY_SELECTED_ITEM_ID, selectedItemID!!)
            detailsFragment.arguments=bundle

            activity?.supportFragmentManager!!.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, detailsFragment)
                .commit()
        }
    }

}
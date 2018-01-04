package architecturedemo.pkg.arun.retail.categorieslist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import architecturedemo.pkg.arun.retail.HomeActivity;
import architecturedemo.pkg.arun.retail.data.models.CategoryData;
import architecturedemo.pkg.arun.retail.databinding.FragmentCategoriesBinding;

/**
 * A fragment representing a list of categories.
 */
public class CategoriesFragment extends Fragment {

    private CategoryViewModel mCategoryViewModel;

    private FragmentCategoriesBinding mCategoryListBinding;

    private CategoryListAdapter mListAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CategoriesFragment() {
    }

    @SuppressWarnings("unused")
    public static CategoriesFragment newInstance() {
        return new CategoriesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mCategoryListBinding = FragmentCategoriesBinding.inflate(inflater, container, false);
        mCategoryViewModel = HomeActivity.obtainViewModel(getActivity());
        mCategoryListBinding.setModel(mCategoryViewModel);
        return mCategoryListBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupListAdapter();
    }

    @Override
    public void onResume() {
        super.onResume();
        mCategoryViewModel.getAllCategories();
    }

    private void setupListAdapter() {
        ListView categoryListView =  mCategoryListBinding.lvCategories;

        mListAdapter = new CategoryListAdapter(
                new ArrayList<CategoryData>(0),
                mCategoryViewModel
        );
        categoryListView.setAdapter(mListAdapter);
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}

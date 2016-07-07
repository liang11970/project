package cn.com.projectdemos.meeting.widget;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class FrameFragment extends Fragment {

	private static final String KEY_CONTENT = "Fragment:Content";

//	protected int mPage = 0;

	public String mContent = "???";

	int resource = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if ((savedInstanceState != null)
				&& savedInstanceState.containsKey(KEY_CONTENT)) {
			mContent = savedInstanceState.getString(KEY_CONTENT);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		return getFragmentResource(inflater, container, savedInstanceState);
	}

	protected abstract View getFragmentResource(LayoutInflater inflater,
												ViewGroup container, Bundle savedInstanceState);

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		doAfterCreate();
		onResumeView();
	}

	protected abstract void doAfterCreate();

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString(KEY_CONTENT, mContent);
	}

	public abstract void onResumeView();

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}

	public int getCurrentIndex() {
		return 0;
	}

}

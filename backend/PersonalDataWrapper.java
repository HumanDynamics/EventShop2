package backend;

import java.util.ArrayList;


public class PersonalDataWrapper extends AbstractDataWrapper {
	
	/**
	 * @param emageParams
	 * @param wrapperParams
	 * @param authFields
	 */
	public PersonalDataWrapper(
			WrapperParams wrapperParams, 
			AuthFields authFields) {
		super(wrapperParams, authFields);
		// TODO add uid
	}

	@Override
	public
	ArrayList<STTPoint> getWrappedData() {
		// TODO Auto-generated method stub
		return null;
	}

}

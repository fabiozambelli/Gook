package biz.fz5.gook.impls;

import aQute.bnd.annotation.component.Component;
import biz.fz5.gook.api.GoodBook;


@Component
public class GoodBookComponent implements GoodBook {

	@Override
	public String getBook() {
		
		return "My Last Book";
	}

}

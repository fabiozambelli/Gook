package biz.fz5.gook.command;

import biz.fz5.gook.api.GoodBook;
import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;

import org.apache.felix.service.command.CommandProcessor;

@Component(properties =	{
		/* Felix GoGo Shell Commands */
		CommandProcessor.COMMAND_SCOPE + ":String=gook",
		CommandProcessor.COMMAND_FUNCTION + ":String=book"
	},
	provide = Object.class
)
public class GoodBookCommand {
	
	private GoodBook goodBook;
	
	@Reference
	public void setGoodBook(GoodBook goodBook) {
		this.goodBook = goodBook;
	}
	
	public void book() {
		System.out.println(goodBook.getBook());
	}

}

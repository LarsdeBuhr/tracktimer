package com.example.ldbtracktimer.listview;

import com.example.ldbtracktimer.model.LapTime;
import javafx.scene.control.ListCell;

/**
 * Repreasentiert eine Zelle die
 * aufgebaut wird und die aktualisierung
 * des Inhalts vornimmt
 */
public class ListViewItemCell extends ListCell<LapTime> {
	
	@Override
	protected void updateItem(LapTime lapTime, boolean empty) {
		super.updateItem(lapTime, empty);
		
		if (empty || lapTime == null) {
			setText(null);
			setGraphic(null);
		} else {
			this.setText(lapTime.getBeautifulGuiText());
		}
	}
	
	
	
	
}

package com.example.ldbtracktimer.listview;

import com.example.ldbtracktimer.model.LapTime;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * Handelt mit einer eigenen
 * Zelle den Aufbau und die Anzeige
 * der ListViewItems
 */
public class ListViewItemCellFactoryCallback implements Callback<ListView<LapTime>, ListCell<LapTime>> {
	
	/**
	 * @param lapTimeListView {@link ListView} - {@link LapTime} : Laptimeliste
	 * @return listViewItemCell : {@link ListCell} - {@link LapTime}
	 */
	@Override
	public ListCell<LapTime> call(ListView<LapTime> lapTimeListView) {
		return  new ListViewItemCell();
	}
}

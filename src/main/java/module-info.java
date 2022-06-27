module com.example.ldbtracktimer {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	
	
	opens com.example.ldbtracktimer to javafx.fxml;
	exports com.example.ldbtracktimer;
}
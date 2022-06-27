package com.example.ldbtracktimer.database;

/**
 * In dieser Klasse werden alle
 * benoetigten SQL-Schluesselwoerter
 * gekapselt und zentral zur Verfuegung gestellt
 */


public abstract class ASqlKeyWords {
	
	//region Datatypes
	protected static final String DATA_TYPE_INTEGER           = " INTEGER ";
	protected static final String DATA_TYPE_INTEGER_INC_COMMA = " INTEGER, ";
	
	protected static final String DATA_TYPE_TEXT_INC_COMMA = " TEXT, ";
	protected static final String DATA_TYPE_TEXT           = " TEXT ";
	//endregion
	
	//region table actions
	protected static final String CREATE_TBL            = "CREATE TABLE ";
	protected static final String SELECT_INC_BLANK      = "SELECT ";
	protected static final String FROM_INC_BLANKS       = " FROM ";
	protected static final String SELECT_ALL_DATA_FROM  = SELECT_INC_BLANK + "*" + FROM_INC_BLANKS;
	protected static final String SELECT_COUNT_FROM_TBL = "SELECT COUNT(*)" + FROM_INC_BLANKS;
	protected static final String DROP_TBL              = "DROP TABLE IF EXISTS ";
	protected static final String ORDER_BY              = " ORDER BY ";
	protected static final String ASC              = " ASC";
	protected static final String TRUNCATE_TABLE              = "TRUNCATE TABLE ";
	protected static final String AUTO_INCREMENT_TABLE_BY_ONE              = " AUTO_INCREMENT = 1";
	
	
	protected static final String INSERT_TBL      = "INSERT INTO ";
	protected static final String UPDATE_TBL      = "UPDATE ";
	protected static final String DELETE_FROM_TBL = "DELETE" + FROM_INC_BLANKS;
	protected static final String ALTER_TBL = "ALTER TABLE ";
	
	//region table actions
	
	protected static final String DROP_TBL_IF_EXISTS = "DROP TABLE IF EXISTS ";
	
	//endregion
	
	//region keys
	protected static final String PRIMARY_KEY_AUTO_INCREMENT_INC_COMMA =
			DATA_TYPE_INTEGER + "PRIMARY KEY AUTOINCREMENT, ";
	protected static final String PRIMARY_KEY_INC_COMMA                = DATA_TYPE_INTEGER + "PRIMARY KEY, ";
	//endregion
	
	//region operators
	protected static final String SET_OPERATOR                     = " SET ";
	protected static final String AND_OPERATOR                     = " AND ";
	protected static final String VALUES_OPERATOR                  = " VALUES ";
	protected static final String EQUALS_OPERATOR                  = " = ";
	protected static final String EQUALS_OPERATOR_INC_PLACE_HOLDER = " =?";
	//endregion
	
	//region conditions
	protected static final String WHERE_CONDITION = " WHERE ";
	
	//endregion
	
	//region chars
	protected static final String CHAR_COMMA                       = ", ";
	protected static final String CHAR_COL_BACK_TICK               = "`";
	protected static final String CHAR_VALUE_BACK_TICK             = "'";
	protected static final String CHAR_SEMICOLON                   = ";";
	protected static final String CHAR_OPEN_PARENTHESIS            = "(";
	protected static final String CHAR_CLOSE_PARENTHESIS           = ")";
	protected static final String CHAR_CLOSE_PARENTHESIS_SEMICOLON = CHAR_CLOSE_PARENTHESIS + CHAR_SEMICOLON;
	
	//endregion
	
	
}

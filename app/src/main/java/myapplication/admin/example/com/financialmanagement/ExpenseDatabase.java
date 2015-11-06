package myapplication.admin.example.com.financialmanagement;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ExpenseDatabase {

	private DatabaseHelper dbHelper;
	private SQLiteDatabase sqLiteDb;

	private Context mCtx = null;

	private static final String DATABASE_NAME = "expense_db";
	private static final int DATABASE_VERSION = 1;

	static final String EXPENSE_TABLE = "expenses";

	static final String INCOME_TABLE = "income";
	static final String MANUAL_TABLE = "manual";

	static final int EXPENSE_INT = 0;

	static String[][] tables = new String[][] { { "_id", "amount",
			"transaction_date","bankName" } };
	static String[][] tables2 = new String[][] { { "_id", "amount",
	"transaction_date" ,"bankName"} };
	
	static String[][] tables3 = new String[][] { { "_id", "note",
		"transaction_date","typeOfItem","sourceOfPayment","money","month","year","day" } };

	private static final String TABLE_1_CREATE = "create table "
			+ EXPENSE_TABLE + " (" + tables[EXPENSE_INT][0]
			+ " integer, " + tables[EXPENSE_INT][1]
			+ " text not null, " + tables[EXPENSE_INT][2] 
			+ " TIMESTAMP DEFAULT CURRENT_TIMESTAMP PRIMARY KEY ," + tables[EXPENSE_INT][3]+ " text not null" + ");";
	
	private static final String TABLE_2_CREATE = "create table "
			+ INCOME_TABLE + " (" + tables2[EXPENSE_INT][0]
			+ " integer, " + tables2[EXPENSE_INT][1]
			+ " text not null, " + tables2[EXPENSE_INT][2]
					+ " TIMESTAMP DEFAULT CURRENT_TIMESTAMP PRIMARY KEY ," + tables2[EXPENSE_INT][3]+ " text not null" + ");";
	
	private static final String TABLE_3_CREATE = "create table "
			+ MANUAL_TABLE + " (" + tables3[EXPENSE_INT][0]
			+ " integer  primary key autoincrement, " + tables3[EXPENSE_INT][1]
			+ " text , " + tables3[EXPENSE_INT][2]
					+ " TIMESTAMP DEFAULT CURRENT_TIMESTAMP ," + tables3[EXPENSE_INT][3]+ " text," + tables3[EXPENSE_INT][4]+ " text,"+ tables3[EXPENSE_INT][5]+ " integer,"+ tables3[EXPENSE_INT][6]+" text not null," + tables3[EXPENSE_INT][7]+" text not null,"+tables3[EXPENSE_INT][8]+" text not null"+");";


	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context ctx) {
			super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(TABLE_1_CREATE);
			db.execSQL(TABLE_2_CREATE);
			db.execSQL(TABLE_3_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + EXPENSE_TABLE);
			onCreate(db);
		}
	}

	/** Constructor */
	public ExpenseDatabase(Context ctx) {
		mCtx = ctx;
	}

	public ExpenseDatabase open() throws SQLException {
		dbHelper = new DatabaseHelper(mCtx);
		sqLiteDb = dbHelper.getWritableDatabase();
		return this;
	}

	public void cleanTable(int tableNo) {
		switch (tableNo) {
		case EXPENSE_INT:
			sqLiteDb.delete(EXPENSE_TABLE, null, null);
			break;
		default:
			break;
		}
	}

	public void close() {
		dbHelper.close();
	}

	public long createAlert(String DATABASE_TABLE, int tableNo, String[] values) {
		ContentValues vals = new ContentValues();
		for (int i = 0; i < values.length; i++) {
			vals.put(tables[tableNo][i + 1], values[i]);
			//Log.d("values"+(i+1),values[i]);
			
		}
		return sqLiteDb.insert(DATABASE_TABLE, null, vals);

	}
	
	public long createAlertIncome(String DATABASE_TABLE, int tableNo, String[] values) {
		ContentValues vals = new ContentValues();
		for (int i = 0; i < values.length; i++) {
			vals.put(tables2[tableNo][i + 1], values[i]);
		}
		return sqLiteDb.insert(DATABASE_TABLE, null, vals);

	}
	
	public long createAlertManual(String DATABASE_TABLE, int tableNo, String[] values) {
		ContentValues vals = new ContentValues();
		for (int i = 0; i < values.length; i++) {
			vals.put(tables3[tableNo][i + 1], values[i]);
		}
		return sqLiteDb.insert(DATABASE_TABLE, null, vals);

	}

	public boolean deleteAlert(String DATABASE_TABLE, int tableNo, int rowId) {
		System.out.println("deleted alert rowId = " + rowId);
		return sqLiteDb.delete(DATABASE_TABLE,
				tables[tableNo][0] + "=" + rowId, null) > 0;
	}

	public Cursor fetchAllAlerts(String DATABASE_TABLE, int tableNo) {
		try {
			return sqLiteDb.query(DATABASE_TABLE, tables[tableNo], null, null,
					null, null, "transaction_date");
		} catch (Exception e) {
			Log.e("yo", e.getMessage());
			return null;
		}
	}
	
	public Cursor fetchAllIncomeAlerts(String DATABASE_TABLE, int tableNo) {
		try {
			return sqLiteDb.query(DATABASE_TABLE, tables2[tableNo], null, null,
					null, null, "transaction_date");
		} catch (Exception e) {
			Log.e("yo", e.getMessage());
			return null;
		}
	}
	
	
	public Cursor fetchAllManualAlerts(String DATABASE_TABLE, int tableNo) {
		try {
			return sqLiteDb.query(DATABASE_TABLE, tables3[tableNo], null, null,
                    null, null, "transaction_date",null);
		} catch (Exception e) {
			Log.e("yo", e.getMessage());
			return null;
		}
	}

	public Cursor fetchAllAlert(String DATABASE_TABLE, int tableNo,
			long frmDate, long tillDate) throws SQLException {
		Cursor ret = sqLiteDb
				.query(DATABASE_TABLE, tables[tableNo], tables[tableNo][2]
						+ " BETWEEN " + frmDate + " AND " + tillDate, null,
						null, null, "transaction_date");
		if (ret != null) {
			ret.moveToFirst();
		}
		return ret;
	}

	public Cursor fetchRecord(String DATABASE_TABLE, int tableNo, String rowID)
			throws SQLException {
		Cursor ret = sqLiteDb.query(DATABASE_TABLE, tables[tableNo],
				tables[tableNo][0] + " = " + rowID, null, null, null, null);
		if (ret != null) {
			ret.moveToFirst();
		}
		return ret;
	}
	
	public Cursor fetchIncomeRecord(String DATABASE_TABLE, int tableNo, String rowID)
			throws SQLException {
		Cursor ret = sqLiteDb.query(DATABASE_TABLE, tables2[tableNo],
				tables2[tableNo][0] + " = " + rowID, null, null, null, null);
		if (ret != null) {
			ret.moveToFirst();
		}
		return ret;
	}

	public boolean updateAlert(String DATABASE_TABLE, int tableNo, int rowId,
			String[] values) {
		try {
			ContentValues vals = new ContentValues();
			// vals.put(tables[tableNo][0], Integer.parseInt(values[0]));
			for (int i = 1; i < values.length; i++) {
				vals.put(tables[tableNo][i], values[i]);
			}
			return sqLiteDb.update(DATABASE_TABLE, vals, tables[tableNo][0]
					+ "=" + rowId, null) > 0;
		} catch (SQLiteConstraintException e) {
			// System.out.println("error in update " + e);
		}
		return false;
	}


    public Cursor fetchAllManualAlertsforpast6months(String DATABASE_TABLE, int tableNo, String startdate, String enddate ) {
        try {

            String demo = "select SUM(money),month from manual" + " where transaction_date between " + "'" + startdate + "'" + " and " + "'" + enddate + "'" + "GROUP BY month";
        //    return sqLiteDb.rawQuery("select * from manual" + " where transaction_date between " + "'" + startdate + "'" + " and " + "'" + enddate + "'" , null);
        return sqLiteDb.rawQuery(demo,null);
        } catch (SQLiteException e) {
            Log.e("yo", e.getMessage());
            return null;
        }
    }

    public Cursor fetchAllManualAlertsforyears(String DATABASE_TABLE, int tableNo,String endyear ) {
        try {

            String demo = "select SUM(money),year from manual" + " where transaction_date <= " + "'" + endyear + "'" + " GROUP BY year";
            //   return sqLiteDb.rawQuery("select * from manual" + " where transaction_date between " + "'" + startdate + "'" + " and " + "'" + enddate + "'" , null);
            return sqLiteDb.rawQuery(demo,null);
        } catch (SQLiteException e) {
            Log.e("yo", e.getMessage());
            return null;
        }
    }

    public Cursor fetchAllManualAlertsforWeeks(String DATABASE_TABLE, int tableNo, String startdate, String enddate ) {
        try {

            String demo = "select SUM(money),day from manual" + " where transaction_date between " + "'" + startdate + "'" + " and " + "'" + enddate + "'" + "GROUP BY day";
            //    return sqLiteDb.rawQuery("select * from manual" + " where transaction_date between " + "'" + startdate + "'" + " and " + "'" + enddate + "'" , null);
            return sqLiteDb.rawQuery(demo,null);
        } catch (SQLiteException e) {
            Log.e("yo", e.getMessage());
            return null;
        }
    }

	public boolean deleteManual(String DATABASE_TABLE, int tableNo, String whereClause){

	    //String whereClause = KEY_CONTENT1 + "= '" + content + "'";

	    
	    return sqLiteDb.delete(DATABASE_TABLE,whereClause ,null) > 0;
	}
}

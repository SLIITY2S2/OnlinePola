package com.madd.mobileapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.madd.mobileapp.models.Cart;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Onlinepola.db";

    //table names
    public static final String TABLE_NAME = "Product";
    public static final String SHIPPING_TABLE = "Shipping";
    public static final String CUSTOMER_TABLE = "Customer";
    public static final String PAYMENT_TABLE = "Payment";
    public static final String ITEM_TABLE = "Item";
    public static final String CART_TABLE = "Cart";


    //Product table
    public static final String COL_1 = "ID";
    public static final String COL_2 = "ProductName";
    public static final String COL_3= "ProductPrice";
    public static final String COL_4 = "ProductImage";

    //Shipping table
    public static final String SHIP_COL_1 = "shipID";
    public static final String SHIP_COL_2 = "firstname";
    public static final String SHIP_COL_3 = "lastname";
    public static final String SHIP_COL_4 = "streetAddress";
    public static final String SHIP_COL_5 = "location";
    public static final String SHIP_COL_6 = "phoneNo";


    //Customer table
    public static final String CUS_COL_1 = "cusId";
    public static final String CUS_COL_2 = "name";
    public static final String CUS_COL_3 = "mobile";
    public static final String CUS_COL_4 = "email";
    public static final String CUS_COL_5 = "password";

    //Payment table
    public static final String PAY_COL_1 = "payId";
    public static final String PAY_COL_2 = "cardNo";
    public static final String PAY_COL_3 = "cardHolder";
    public static final String PAY_COL_4 = "expireDate";
    public static final String PAY_COL_5 = "CVV";
    public static final String PAY_COL_6 = "sum";
    public static final String PAY_COL_7 = "fname";

    //Item
    public static final String ITEM_COL_1 = "Itemcode";
    public static final String ITEM_COL_2 = "Itemname";
    public static final String ITEM_COL_3 = "qty";
    public static final String ITEM_COL_4 = "price";


    //Cart
    public static final String CART_COL_1 = "CartID";
    public static final String CART_COL_2 = "ProductName";
    public static final String CART_COL_3= "ProductPrice";
    public static final String CART_COL_4 = "qty";
    public static final String CART_COL_5 = "fname";



    private String[] selectionArgs;


    public Database(@Nullable Context context) {
        super(context,DATABASE_NAME,null,2);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,ProductName TEXT,ProductPrice FLOAT,ProductImage BLOB) ") ;
        sqLiteDatabase.execSQL("create table "+CUSTOMER_TABLE+ "(cusId INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, mobile INTEGER, email TEXT, password TEXT)");
        sqLiteDatabase.execSQL("create table "+SHIPPING_TABLE + " (shipID INTEGER PRIMARY KEY AUTOINCREMENT , firstname TEXT, lastname TEXT, streetAddress TEXT, location TEXT, phoneNo INTEGER)");
        sqLiteDatabase.execSQL(" create table " +PAYMENT_TABLE+ " (payId INTEGER PRIMARY KEY AUTOINCREMENT, cardNo INTEGER, cardHolder TEXT, expireDate TEXT, CVV INTEGER, sum FLOAT, fname TEXT)");
        sqLiteDatabase.execSQL("create table " + ITEM_TABLE+ "(Itemcode INTEGER PRIMARY KEY AUTOINCREMENT, Itemname TEXT, qty INTEGER, price FLOAT) ");
        sqLiteDatabase.execSQL("create table " + CART_TABLE+ "(CartID INTEGER PRIMARY KEY AUTOINCREMENT, ProductName TEXT, ProductPrice Double,  qty INTEGER, fname TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+CUSTOMER_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SHIPPING_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +PAYMENT_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ITEM_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CART_TABLE);
        onCreate(sqLiteDatabase);
    }

    //Product
    public boolean insertData(String pname, String pprice, AppCompatImageView image){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,pname);
        contentValues.put(COL_3,pprice);
        contentValues.put(COL_4, String.valueOf(image));

        long result = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

    public Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase myDb = this.getReadableDatabase();

        Cursor cursor = null;
        if(myDb != null){
            cursor = myDb.rawQuery(query,null);
        }
        return cursor;
    }

    public void deleteProduct(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME,"ID = ?", new String[] {id});
    }

    //Shipping

    public boolean insertShipping(String fname, String lname, String streetAddress, String location, int phoneNo){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SHIP_COL_2,fname);
        contentValues.put(SHIP_COL_3,lname);
        contentValues.put(SHIP_COL_4, streetAddress);
        contentValues.put(SHIP_COL_5,location);
        contentValues.put(SHIP_COL_6, phoneNo);


        long result = sqLiteDatabase.insert(SHIPPING_TABLE,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

    //Customer

    public boolean insertCustomer(String name, int mobileno, String email, String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CUS_COL_2,name);
        contentValues.put(CUS_COL_3,mobileno);
        contentValues.put(CUS_COL_4,email);
        contentValues.put(CUS_COL_5,password);

        long result = sqLiteDatabase.insert(CUSTOMER_TABLE,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    Cursor readAllCustomer(){
        String query = "SELECT * FROM " + CUSTOMER_TABLE;
        SQLiteDatabase myDb = this.getReadableDatabase();

        Cursor cursor = null;
        if(myDb != null){
            cursor = myDb.rawQuery(query,null);
        }
        return cursor;
    }

    public void deleteCustomer(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(CUSTOMER_TABLE,"cusId = ?", new String[] {id});
    }

    public boolean Login(String email, String password){

        String[] columns = {"email"};
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();


        selectionArgs = new String[]{email,password};

        Cursor  c = sqLiteDatabase.rawQuery("select * from "+CUSTOMER_TABLE+ " where email= ? and password= ?", selectionArgs);
        if (c != null) {
            if(c.getCount() > 0)
            {
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }

    public boolean insertDataPayment(int cardNo, String cardHolder, String expireDate, int CVV, double sum, String fname){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PAY_COL_2,cardNo);
        contentValues.put(PAY_COL_3,cardHolder);
        contentValues.put(PAY_COL_4, expireDate);
        contentValues.put(PAY_COL_5, CVV);
        contentValues.put(PAY_COL_6, sum);
        contentValues.put(PAY_COL_7, fname);


        long result = sqLiteDatabase.insert(PAYMENT_TABLE,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

    //insert Item
    public boolean insertitem(String Itemname, int qty, double price){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ITEM_COL_2,Itemname);
        contentValues.put(ITEM_COL_3,qty);
        contentValues.put(ITEM_COL_4,price);


        long result = sqLiteDatabase.insert(ITEM_TABLE,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

    //insert Cart
    public boolean insertCart1(String ProductName, double price, int qty){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CART_COL_2,ProductName);
        contentValues.put(CART_COL_3,price);
        contentValues.put(CART_COL_4,qty);

        long result = sqLiteDatabase.insert(CART_TABLE,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor readCartItem(){
        String query = "SELECT * FROM " + CART_TABLE;
        SQLiteDatabase myDb = this.getReadableDatabase();

        Cursor cursor = null;
        if(myDb != null){
            cursor = myDb.rawQuery(query,null);
        }
        return cursor;
    }

    public void deleteCartItem(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(CART_TABLE,"CartID = "+ id,null);
        sqLiteDatabase.close();
    }

    public void updateCartItem(int ID,double price,int qty){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(CART_COL_3,price);
        values.put(CART_COL_4,qty);

        sqLiteDatabase.update(CART_TABLE, values, "CartID= "+ID,null );
        sqLiteDatabase.close();
    }

    public int countCart(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT COUNT(*) FROM " +CART_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        cursor.close();
        return count;


    }

    public boolean isAddToCart(int ID){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT EXISTS (SELECT 1 FROM " +CART_TABLE+ " WHERE CartID = ID )";
        selectionArgs = new String[]{String.valueOf(ID)};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery(query, selectionArgs);

        if(result == null){
            return false;
        }
        else{
            return true;
        }



    }


    public boolean insertCart(Cart cart) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CART_COL_2,cart.getPname());
        contentValues.put(CART_COL_3,cart.getPprice());
        contentValues.put(CART_COL_4,cart.getQty());


        long result = sqLiteDatabase.insert(CART_TABLE,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

    public boolean insertProductToCart(String pname,int qty){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CART_COL_2,pname);
        contentValues.put(CART_COL_4,qty);


        long result = sqLiteDatabase.insert(CART_TABLE,null,contentValues);
        if(result == -1) {
            return false;
        }
        else{
            return true;

        }

    }

    public ArrayList<Cart> getCartItems() {
        ArrayList<Cart> arrayList = new ArrayList<>();

        String select_query= "SELECT *FROM " + CART_TABLE;

        SQLiteDatabase db = this .getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        if (cursor.moveToFirst()) {
            do {
                Cart cart = new Cart();
                cart.setId(Integer.parseInt(cursor.getString(0)));
                cart.setPname(cursor.getString(1));
                cart.setPprice(Double.parseDouble(cursor.getString(2)));
                cart.setQty(cursor.getInt(3));
                arrayList.add(cart);
            }while (cursor.moveToNext());
        }
        return arrayList;
    }

}

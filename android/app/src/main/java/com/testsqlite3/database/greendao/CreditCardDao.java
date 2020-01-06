package com.testsqlite3.database.greendao;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import com.testsqlite3.database.entity.CreditCard;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CREDIT_CARD".
*/
public class CreditCardDao extends AbstractDao<CreditCard, Long> {

    public static final String TABLENAME = "CREDIT_CARD";

    /**
     * Properties of entity CreditCard.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property StudentId = new Property(1, Long.class, "studentId", false, "STUDENT_ID");
        public final static Property UserName = new Property(2, String.class, "userName", false, "USER_NAME");
        public final static Property CardNum = new Property(3, String.class, "cardNum", false, "CARD_NUM");
        public final static Property WhichBank = new Property(4, String.class, "whichBank", false, "WHICH_BANK");
        public final static Property CardType = new Property(5, Integer.class, "cardType", false, "CARD_TYPE");
    }

    private Query<CreditCard> student_MCreditCardListQuery;

    public CreditCardDao(DaoConfig config) {
        super(config);
    }
    
    public CreditCardDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CREDIT_CARD\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"STUDENT_ID\" INTEGER NOT NULL ," + // 1: studentId
                "\"USER_NAME\" TEXT NOT NULL ," + // 2: userName
                "\"CARD_NUM\" TEXT," + // 3: cardNum
                "\"WHICH_BANK\" TEXT," + // 4: whichBank
                "\"CARD_TYPE\" INTEGER);"); // 5: cardType
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CREDIT_CARD\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, CreditCard entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getStudentId());
        stmt.bindString(3, entity.getUserName());
 
        String cardNum = entity.getCardNum();
        if (cardNum != null) {
            stmt.bindString(4, cardNum);
        }
 
        String whichBank = entity.getWhichBank();
        if (whichBank != null) {
            stmt.bindString(5, whichBank);
        }
 
        Integer cardType = entity.getCardType();
        if (cardType != null) {
            stmt.bindLong(6, cardType);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, CreditCard entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getStudentId());
        stmt.bindString(3, entity.getUserName());
 
        String cardNum = entity.getCardNum();
        if (cardNum != null) {
            stmt.bindString(4, cardNum);
        }
 
        String whichBank = entity.getWhichBank();
        if (whichBank != null) {
            stmt.bindString(5, whichBank);
        }
 
        Integer cardType = entity.getCardType();
        if (cardType != null) {
            stmt.bindLong(6, cardType);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public CreditCard readEntity(Cursor cursor, int offset) {
        CreditCard entity = new CreditCard( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getLong(offset + 1), // studentId
            cursor.getString(offset + 2), // userName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // cardNum
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // whichBank
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5) // cardType
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, CreditCard entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setStudentId(cursor.getLong(offset + 1));
        entity.setUserName(cursor.getString(offset + 2));
        entity.setCardNum(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setWhichBank(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setCardType(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(CreditCard entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(CreditCard entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(CreditCard entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "mCreditCardList" to-many relationship of Student. */
    public List<CreditCard> _queryStudent_MCreditCardList(Long studentId) {
        synchronized (this) {
            if (student_MCreditCardListQuery == null) {
                QueryBuilder<CreditCard> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.StudentId.eq(null));
                student_MCreditCardListQuery = queryBuilder.build();
            }
        }
        Query<CreditCard> query = student_MCreditCardListQuery.forCurrentThread();
        query.setParameter(0, studentId);
        return query.list();
    }

}

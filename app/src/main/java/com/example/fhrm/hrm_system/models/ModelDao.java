package com.example.fhrm.hrm_system.models;

import java.sql.SQLException;

/**
 * Created by luuhoangtruc on 20/01/2016.
 */
public interface ModelDao<T> {
  long insert(T object) throws SQLException;
}

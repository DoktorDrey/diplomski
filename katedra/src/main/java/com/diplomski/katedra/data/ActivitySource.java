package com.diplomski.katedra.data;

import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.grid.SortConstraint;

import java.util.List;

/**
 * Created by andrija on 8/10/15.
 */
public class ActivitySource implements GridDataSource {
    @Override
    public int getAvailableRows() {
        return 0;
    }

    @Override
    public void prepare(int i, int i2, List<SortConstraint> sortConstraints) {

    }

    @Override
    public Object getRowValue(int i) {
        return null;
    }

    @Override
    public Class getRowType() {
        return null;
    }
}

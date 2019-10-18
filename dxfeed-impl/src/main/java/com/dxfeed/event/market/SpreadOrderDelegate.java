/*
 * !++
 * QDS - Quick Data Signalling Library
 * !-
 * Copyright (C) 2002 - 2019 Devexperts LLC
 * !-
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 * If a copy of the MPL was not distributed with this file, You can obtain one at
 * http://mozilla.org/MPL/2.0/.
 * !__
 */
package com.dxfeed.event.market;

import java.util.EnumSet;

import com.devexperts.qd.DataRecord;
import com.devexperts.qd.QDContract;
import com.devexperts.qd.ng.RecordBuffer;
import com.devexperts.qd.ng.RecordCursor;
import com.dxfeed.api.impl.EventDelegateFlags;
import com.dxfeed.event.IndexedEventSource;
import com.dxfeed.event.market.impl.SpreadOrderMapping;

public final class SpreadOrderDelegate extends OrderBaseDelegateImpl<SpreadOrder> {
// BEGIN: CODE AUTOMATICALLY GENERATED: DO NOT MODIFY. IT IS REGENERATED BY com.dxfeed.api.codegen.ImplCodeGen
    private final SpreadOrderMapping m;

    public SpreadOrderDelegate(DataRecord record, QDContract contract, EnumSet<EventDelegateFlags> flags) {
        super(record, contract, flags);
        m = record.getMapping(SpreadOrderMapping.class);
    }

    @Override
    public SpreadOrderMapping getMapping() {
        return m;
    }

    @Override
    public SpreadOrder createEvent() {
        return new SpreadOrder();
    }

    @Override
    public SpreadOrder getEvent(SpreadOrder event, RecordCursor cursor) {
        super.getEvent(event, cursor);
        event.setEventFlags(cursor.getEventFlags());
        event.setIndex(((long) getSource().id() << 32) | (m.getIndex(cursor) & 0xFFFFFFFFL));
        event.setTimeSequence((((long) m.getTimeSeconds(cursor)) << 32) | (m.getSequence(cursor) & 0xFFFFFFFFL));
        event.setTimeNanoPart(m.getTimeNanoPart(cursor));
        event.setPrice(m.getPrice(cursor));
        event.setSizeAsDouble(m.getSizeDouble(cursor));
        event.setExecutedSize(m.getExecutedSize(cursor));
        event.setCount(m.getCount(cursor));
        event.setFlags(m.getFlags(cursor));
        event.setSpreadSymbol(m.getSpreadSymbol(cursor));
        return event;
    }

    @Override
    public RecordCursor putEvent(SpreadOrder event, RecordBuffer buf) {
        RecordCursor cursor = super.putEvent(event, buf);
        cursor.setEventFlags(event.getEventFlags());
        int index = (int) event.getIndex();
        m.setIndex(cursor, index);
        m.setTimeSeconds(cursor, (int) (event.getTimeSequence() >>> 32));
        m.setSequence(cursor, (int) event.getTimeSequence());
        m.setTimeNanoPart(cursor, event.getTimeNanoPart());
        m.setPrice(cursor, event.getPrice());
        m.setSizeDouble(cursor, event.getSizeAsDouble());
        m.setExecutedSize(cursor, event.getExecutedSize());
        m.setCount(cursor, (int) event.getCount());
        m.setFlags(cursor, event.getFlags());
        m.setSpreadSymbol(cursor, event.getSpreadSymbol());
        if (index < 0)
            throw new IllegalArgumentException("Invalid index to publish");
        if ((event.getEventFlags() & OrderBase.SNAPSHOT_END) != 0 && index != 0)
            throw new IllegalArgumentException("SNAPSHOT_END event must have index == 0");
        if ((event.getEventFlags() & OrderBase.REMOVE_EVENT) == 0 && event.getOrderSide() == Side.UNDEFINED)
            throw new IllegalArgumentException("only REMOVE_EVENT event can have side == UNDEFINED");
        return cursor;
    }

    @Override
    public IndexedEventSource getSource() {
        return m.getRecordSource();
    }
// END: CODE AUTOMATICALLY GENERATED
}

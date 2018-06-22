/*
 * !++
 * QDS - Quick Data Signalling Library
 * !-
 * Copyright (C) 2002 - 2018 Devexperts LLC
 * !-
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 * If a copy of the MPL was not distributed with this file, You can obtain one at
 * http://mozilla.org/MPL/2.0/.
 * !__
 */
package com.dxfeed.event.option;

import java.util.*;

import com.devexperts.qd.*;
import com.devexperts.qd.ng.RecordMapping;
import com.devexperts.qd.ng.RecordMappingFactory;
import com.devexperts.services.ServiceProvider;
import com.dxfeed.api.impl.*;
import com.dxfeed.event.option.impl.*;

@ServiceProvider(order = -50)
public final class OptionFactoryImpl extends EventDelegateFactory implements RecordMappingFactory {
// BEGIN: CODE AUTOMATICALLY GENERATED: DO NOT MODIFY. IT IS REGENERATED BY com.dxfeed.api.codegen.ImplCodeGen
    @Override
    public void buildScheme(SchemeBuilder builder) {
        builder.addOptionalField("Greeks", "Time", SerialFieldType.TIME, "Greeks", "Time", true, SchemeFieldTime.FIRST_TIME_INT_FIELD);
        builder.addOptionalField("Greeks", "Sequence", SerialFieldType.SEQUENCE, "Greeks", "Sequence", true, SchemeFieldTime.SECOND_TIME_INT_FIELD);
        builder.addRequiredField("Greeks", "Greeks.Price", SerialFieldType.DECIMAL);
        builder.addRequiredField("Greeks", "Volatility", SerialFieldType.DECIMAL);
        builder.addRequiredField("Greeks", "Delta", SerialFieldType.DECIMAL);
        builder.addRequiredField("Greeks", "Gamma", SerialFieldType.DECIMAL);
        builder.addRequiredField("Greeks", "Theta", SerialFieldType.DECIMAL);
        builder.addRequiredField("Greeks", "Rho", SerialFieldType.DECIMAL);
        builder.addRequiredField("Greeks", "Vega", SerialFieldType.DECIMAL);

        builder.addRequiredField("TheoPrice", "Theo.Time", SerialFieldType.TIME, SchemeFieldTime.FIRST_TIME_INT_FIELD);
        builder.addOptionalField("TheoPrice", "Theo.Sequence", SerialFieldType.SEQUENCE, "TheoPrice", "Sequence", true, SchemeFieldTime.SECOND_TIME_INT_FIELD);
        builder.addRequiredField("TheoPrice", "Theo.Price", SerialFieldType.DECIMAL);
        builder.addRequiredField("TheoPrice", "Theo.UnderlyingPrice", SerialFieldType.DECIMAL);
        builder.addRequiredField("TheoPrice", "Theo.Delta", SerialFieldType.DECIMAL);
        builder.addRequiredField("TheoPrice", "Theo.Gamma", SerialFieldType.DECIMAL);
        builder.addOptionalField("TheoPrice", "Theo.Dividend", SerialFieldType.DECIMAL, "TheoPrice", "Dividend", true);
        builder.addOptionalField("TheoPrice", "Theo.Interest", SerialFieldType.DECIMAL, "TheoPrice", "Interest", true);

        builder.addOptionalField("Underlying", "Time", SerialFieldType.TIME, "Underlying", "Time", true, SchemeFieldTime.FIRST_TIME_INT_FIELD);
        builder.addOptionalField("Underlying", "Sequence", SerialFieldType.SEQUENCE, "Underlying", "Sequence", true, SchemeFieldTime.SECOND_TIME_INT_FIELD);
        builder.addOptionalField("Underlying", "Volatility", SerialFieldType.DECIMAL, "Underlying", "Volatility", true);
        builder.addOptionalField("Underlying", "FrontVolatility", SerialFieldType.DECIMAL, "Underlying", "FrontVolatility", true);
        builder.addOptionalField("Underlying", "BackVolatility", SerialFieldType.DECIMAL, "Underlying", "BackVolatility", true);
        builder.addOptionalField("Underlying", "PutCallRatio", SerialFieldType.DECIMAL, "Underlying", "PutCallRatio", true);

        builder.addOptionalField("Series", "Void", SerialFieldType.VOID, "Series", "Void", true, SchemeFieldTime.FIRST_TIME_INT_FIELD);
        builder.addOptionalField("Series", "Index", SerialFieldType.COMPACT_INT, "Series", "Index", true, SchemeFieldTime.SECOND_TIME_INT_FIELD);
        builder.addOptionalField("Series", "Time", SerialFieldType.TIME, "Series", "Time", true);
        builder.addOptionalField("Series", "Sequence", SerialFieldType.SEQUENCE, "Series", "Sequence", true);
        builder.addRequiredField("Series", "Expiration", SerialFieldType.DATE);
        builder.addRequiredField("Series", "Volatility", SerialFieldType.DECIMAL);
        builder.addRequiredField("Series", "PutCallRatio", SerialFieldType.DECIMAL);
        builder.addRequiredField("Series", "ForwardPrice", SerialFieldType.DECIMAL);
        builder.addOptionalField("Series", "Dividend", SerialFieldType.DECIMAL, "Series", "Dividend", true);
        builder.addOptionalField("Series", "Interest", SerialFieldType.DECIMAL, "Series", "Interest", true);
    }

    @Override
    public Collection<EventDelegate<?>> createDelegates(DataRecord record) {
        Collection<EventDelegate<?>> result = new ArrayList<>();
        if (record.getMapping(GreeksMapping.class) != null) {
            result.add(new GreeksDelegate(record, QDContract.TICKER, EnumSet.of(EventDelegateFlags.SUB, EventDelegateFlags.PUB)));
            result.add(new GreeksDelegate(record, QDContract.STREAM, EnumSet.of(EventDelegateFlags.PUB, EventDelegateFlags.WILDCARD)));
            result.add(new GreeksDelegate(record, QDContract.HISTORY, EnumSet.of(EventDelegateFlags.SUB, EventDelegateFlags.PUB, EventDelegateFlags.TIME_SERIES)));
        } else if (record.getMapping(TheoPriceMapping.class) != null) {
            result.add(new TheoPriceDelegate(record, QDContract.TICKER, EnumSet.of(EventDelegateFlags.SUB, EventDelegateFlags.PUB)));
            result.add(new TheoPriceDelegate(record, QDContract.STREAM, EnumSet.of(EventDelegateFlags.PUB, EventDelegateFlags.WILDCARD)));
            result.add(new TheoPriceDelegate(record, QDContract.HISTORY, EnumSet.of(EventDelegateFlags.SUB, EventDelegateFlags.PUB, EventDelegateFlags.TIME_SERIES)));
        } else if (record.getMapping(UnderlyingMapping.class) != null) {
            result.add(new UnderlyingDelegate(record, QDContract.TICKER, EnumSet.of(EventDelegateFlags.SUB, EventDelegateFlags.PUB)));
            result.add(new UnderlyingDelegate(record, QDContract.STREAM, EnumSet.of(EventDelegateFlags.PUB, EventDelegateFlags.WILDCARD)));
            result.add(new UnderlyingDelegate(record, QDContract.HISTORY, EnumSet.of(EventDelegateFlags.SUB, EventDelegateFlags.PUB, EventDelegateFlags.TIME_SERIES)));
        } else if (record.getMapping(SeriesMapping.class) != null) {
            result.add(new SeriesDelegate(record, QDContract.STREAM, EnumSet.of(EventDelegateFlags.PUB, EventDelegateFlags.WILDCARD)));
            result.add(new SeriesDelegate(record, QDContract.HISTORY, EnumSet.of(EventDelegateFlags.SUB, EventDelegateFlags.PUB)));
        }
        return result;
    }

    @Override
    public Collection<EventDelegate<?>> createStreamOnlyDelegates(DataRecord record) {
        Collection<EventDelegate<?>> result = new ArrayList<>();
        if (record.getMapping(GreeksMapping.class) != null) {
            result.add(new GreeksDelegate(record, QDContract.STREAM, EnumSet.of(EventDelegateFlags.SUB, EventDelegateFlags.PUB, EventDelegateFlags.WILDCARD)));
        } else if (record.getMapping(TheoPriceMapping.class) != null) {
            result.add(new TheoPriceDelegate(record, QDContract.STREAM, EnumSet.of(EventDelegateFlags.SUB, EventDelegateFlags.PUB, EventDelegateFlags.WILDCARD)));
        } else if (record.getMapping(UnderlyingMapping.class) != null) {
            result.add(new UnderlyingDelegate(record, QDContract.STREAM, EnumSet.of(EventDelegateFlags.SUB, EventDelegateFlags.PUB, EventDelegateFlags.WILDCARD)));
        } else if (record.getMapping(SeriesMapping.class) != null) {
            result.add(new SeriesDelegate(record, QDContract.STREAM, EnumSet.of(EventDelegateFlags.SUB, EventDelegateFlags.PUB, EventDelegateFlags.WILDCARD)));
        }
        return result;
    }

    @Override
    public RecordMapping createMapping(DataRecord record) {
        String baseRecordName = getBaseRecordName(record.getName());
        if (baseRecordName.equals("Greeks"))
            return new GreeksMapping(record);
        if (baseRecordName.equals("TheoPrice"))
            return new TheoPriceMapping(record);
        if (baseRecordName.equals("Underlying"))
            return new UnderlyingMapping(record);
        if (baseRecordName.equals("Series"))
            return new SeriesMapping(record);
        return null;
    }
// END: CODE AUTOMATICALLY GENERATED
}

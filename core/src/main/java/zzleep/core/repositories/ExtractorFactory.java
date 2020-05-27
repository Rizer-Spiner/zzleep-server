package zzleep.core.repositories;

import zzleep.core.models.*;

import java.time.LocalDateTime;

public class ExtractorFactory {
    //preferences
    private static final Context.ResultSetExtractor<Preferences> preferencesExtractor = row ->
            new Preferences(row.getString(DatabaseConstants.PREFERENCES_COL_DEVICE_ID), row.getBoolean(DatabaseConstants.PREFERENCES_COL_REGULATIONS_ENABLED), row.getInt(DatabaseConstants.PREFERENCES_COL_CO2_MAX), row.getDouble(DatabaseConstants.PREFERENCES_COL_HUMIDITY_MIN), row.getDouble(DatabaseConstants.PREFERENCES_COL_HUMIDITY_MAX), row.getInt(DatabaseConstants.PREFERENCES_COL_TEMPERATURE_MIN), row.getInt(DatabaseConstants.PREFERENCES_COL_TEMPERATURE_MAX));

    public static Context.ResultSetExtractor<Preferences> getPreferencesExtractor() {
        return preferencesExtractor;
    }

    //sleep
    private static final Context.ResultSetExtractor<Sleep> sleepExtractor = row ->
            new Sleep(row.getInt(DatabaseConstants.SLEEP_COL_SLEEP_ID), row.getString(DatabaseConstants.SLEEP_COL_DEVICE_ID), row.getObject(DatabaseConstants.SLEEP_COL_START_TIME, LocalDateTime.class), row.getObject(DatabaseConstants.SLEEP_COL_FINISH_TIME, LocalDateTime.class), row.getInt(DatabaseConstants.SLEEP_COL_RATING));

    public static Context.ResultSetExtractor<Sleep> getSleepExtractor() {
        return sleepExtractor;
    }

    //facts
    private static final Context.ResultSetExtractor<Fact> factsExtractor = row -> new Fact(
            row.getInt(DatabaseConstants.FACT_COL_FACT_ID),
            row.getString(DatabaseConstants.FACT_COL_TITLE),
            row.getString(DatabaseConstants.FACT_COL_CONTENT)
    );

    public static Context.ResultSetExtractor<Fact> getFactsExtractor() {
        return factsExtractor;
    }

    //room conditions
    private static final Context.ResultSetExtractor<RoomCondition> roomConditionsExtractor = row ->
            new RoomCondition(row.getInt(DatabaseConstants.RC_COL_SLEEP_ID), row.getObject(DatabaseConstants.RC_COL_TIME, LocalDateTime.class), row.getInt(DatabaseConstants.RC_COL_TEMPERATURE), row.getInt(DatabaseConstants.RC_COL_CO2), row.getDouble(DatabaseConstants.RC_COL_SOUND), row.getDouble(DatabaseConstants.RC_COL_HUMIDITY));

    public static Context.ResultSetExtractor<RoomCondition> getRoomConditionsExtractor() {
        return roomConditionsExtractor;
    }

    //sleep sessions
    private static final Context.ResultSetExtractor<SleepSession> sleepSessionExtractor = row -> new SleepSession(
            row.getInt(DatabaseConstants.SLEEP_SESSION_COL_SLEEP_ID),
            row.getString(DatabaseConstants.SLEEP_SESSION_COL_DEVICE_ID),
            row.getObject(DatabaseConstants.SLEEP_SESSION_COL_TIME_START, LocalDateTime.class),
            row.getObject(DatabaseConstants.SLEEP_SESSION_COL_TIME_FINISH, LocalDateTime.class),
            row.getInt(DatabaseConstants.SLEEP_SESSION_COL_RATING),
            row.getDouble(DatabaseConstants.SLEEP_SESSION_COL_AVERAGE_CO2),
            row.getDouble(DatabaseConstants.SLEEP_SESSION_COL_AVERAGE_HUMIDITY),
            row.getDouble(DatabaseConstants.SLEEP_SESSION_COL_AVERAGE_SOUND),
            row.getDouble(DatabaseConstants.SLEEP_SESSION_COL_AVERAGE_TEMPERATURE)
    );

    public static Context.ResultSetExtractor<SleepSession> getSleepSessionExtractor() {
        return sleepSessionExtractor;
    }

    //dw room conditions
    private static final Context.ResultSetExtractor<RoomCondition> dwRoomConditionExtractor = row -> new RoomCondition(
            row.getInt(DatabaseConstants.DW_COL_SLEEP_ID),
            row.getObject(DatabaseConstants.DW_COL_TIMESTAMP, LocalDateTime.class),
            row.getInt(DatabaseConstants.DW_COL_TEMPERATURE),
            row.getInt(DatabaseConstants.DW_COL_CO2),
            row.getDouble(DatabaseConstants.DW_COL_SOUND),
            row.getDouble(DatabaseConstants.DW_COL_HUMIDITY)
    );

    public static Context.ResultSetExtractor<RoomCondition> getDWRoomConditionExtractor() {
        return dwRoomConditionExtractor;
    }


    //devices
    private static Context.ResultSetExtractor<Device> deviceExtractor = row -> new Device(
            row.getString(DatabaseConstants.DEVICE_COL_ID),
            row.getString(DatabaseConstants.DEVICE_COL_ROOM_NAME),
            row.getString(DatabaseConstants.DEVICE_COL_USER_ID)
    );

    public static Context.ResultSetExtractor<Device> getDeviceExtractor() {
        return deviceExtractor;
    }
}

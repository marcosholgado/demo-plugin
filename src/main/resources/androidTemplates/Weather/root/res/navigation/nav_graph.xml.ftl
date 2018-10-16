<?xml version="1.0" encoding="utf-8"?>
<navigation>

    <fragment
        android:id="@+id/${city?lower_case}WeatherAction"
        android:name="com.marcosholgado.weather.WeatherFragment"
        android:label="Weather">
        <argument
            android:name="city"
            app:argType="string"
            android:defaultValue="${city}"
            />
    </fragment>

</navigation>
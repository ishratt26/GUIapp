/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicweather;

/**
 *
 * @author tunazzinaIshrat
 */
public class TestWeather
{

	public static void main(String[] args)
	{
		WeatherAPI weather = new WeatherAPI("44418");
		System.out.println(weather.theWeatherRSS);
		for(int i = 0; i < weather.weatherForecastList.size(); i++)
		{
			System.out.println(weather.weatherForecastList.get(i).lowTemp + " " +
			weather.weatherForecastList.get(i).highTemp + " "+ weather.weatherForecastList.get(i).textTemp);
		}
	}
}

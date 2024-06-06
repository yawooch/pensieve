package com.pjt.pensieve.wc.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class SpecialDateApiClient
{
    private String baseURL = "http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/";
    private String serviceKey = "ZvQj%2BdF7Qii%2FQOORAN4qLXkSnfUuR%2Bcf8Wg00ku7ZWwfnLmNlS%2F%2Bpi2YaWjmGJIXxLWSI5uHZp%2FIwqDDFO5VFA%3D%3D";

    // 국경일 정보조회
    public SpecialDateResponse getHoliDeInfo(String solYear) throws RestClientException, URISyntaxException
    {
        BufferedReader                  reader = null;
        StringBuilder               urlBuilder = null;
        StringBuilder      responseTextBuilder = null;
        HttpURLConnection           connection = null;
        SpecialDateResponse specialDateRespose = null;

        try
        {
            urlBuilder = new StringBuilder(baseURL + "getHoliDeInfo");

            urlBuilder.append("?ServiceKey=").append(serviceKey);
//                urlBuilder.append("&solYear=").append(solYear);
            urlBuilder.append("&solYear=").append(solYear);
//                urlBuilder.append("&solMonth=").append();
            urlBuilder.append("&_type=").append("json");
            urlBuilder.append("&numOfRows=").append("20");
            
            log.info("Request URL : {}", urlBuilder.toString());

            connection = (HttpURLConnection) new URL(urlBuilder.toString()).openConnection();
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestMethod("GET");

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            responseTextBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null)
            {
                responseTextBuilder.append(line);
            }
            ObjectMapper mapper = new ObjectMapper();

            JsonParser parser;
            
            parser = mapper.createParser(responseTextBuilder.toString());
            specialDateRespose = parser.readValueAs(SpecialDateResponse.class);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                reader.close();
                connection.disconnect();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        return specialDateRespose;

    }
    

    // 공휴일 정보조회(제헌절 제외한 국경일)
    public SpecialDateResponse getRestDeInfo(String solYear) throws RestClientException, URISyntaxException
    {
        BufferedReader                  reader = null;
        StringBuilder               urlBuilder = null;
        StringBuilder      responseTextBuilder = null;
        HttpURLConnection           connection = null;
        SpecialDateResponse specialDateRespose = null;

        try
        {
            urlBuilder = new StringBuilder(baseURL + "getRestDeInfo");

            urlBuilder.append("?ServiceKey=").append(serviceKey);
            urlBuilder.append("&solYear=").append(solYear);
//                urlBuilder.append("&solMonth=").append();
            urlBuilder.append("&_type=").append("json");
            urlBuilder.append("&numOfRows=").append("20");
            
            log.info("Request URL : {}", urlBuilder.toString());

            connection = (HttpURLConnection) new URL(urlBuilder.toString()).openConnection();
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestMethod("GET");

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            responseTextBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null)
            {
                responseTextBuilder.append(line);
            }
            ObjectMapper mapper = new ObjectMapper();

            JsonParser parser;
            
            parser = mapper.createParser(responseTextBuilder.toString());
            specialDateRespose = parser.readValueAs(SpecialDateResponse.class);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                reader.close();
                connection.disconnect();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        return specialDateRespose;
    }

    // 기념일 정보 조회
    public SpecialDateResponse getAnniversaryInfo(String solYear) throws RestClientException, URISyntaxException
    {
        BufferedReader                  reader = null;
        StringBuilder               urlBuilder = null;
        StringBuilder      responseTextBuilder = null;
        HttpURLConnection           connection = null;
        SpecialDateResponse specialDateRespose = null;

        try
        {
            urlBuilder = new StringBuilder(baseURL + "getAnniversaryInfo");

            urlBuilder.append("?ServiceKey=").append(serviceKey);
            urlBuilder.append("&solYear=").append(solYear);
//                urlBuilder.append("&solMonth=").append();
            urlBuilder.append("&_type=").append("json");
            urlBuilder.append("&numOfRows=").append("80");
            
            log.info("Request URL : {}", urlBuilder.toString());

            connection = (HttpURLConnection) new URL(urlBuilder.toString()).openConnection();
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestMethod("GET");

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            responseTextBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null)
            {
                responseTextBuilder.append(line);
            }
            ObjectMapper mapper = new ObjectMapper();

            JsonParser parser;
            
            parser = mapper.createParser(responseTextBuilder.toString());
            specialDateRespose = parser.readValueAs(SpecialDateResponse.class);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                reader.close();
                connection.disconnect();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        return specialDateRespose;
    }
    

    // 잡절 정보 조회
    public AstronomyDateResponse getSundryDayInfo(String solYear) throws RestClientException, URISyntaxException
    {
        BufferedReader                       reader = null;
        StringBuilder                    urlBuilder = null;
        StringBuilder           responseTextBuilder = null;
        HttpURLConnection                connection = null;
        AstronomyDateResponse astronomyDateResponse = null;

        try
        {
            urlBuilder = new StringBuilder(baseURL + "getSundryDayInfo");

            urlBuilder.append("?ServiceKey=").append(serviceKey);
            urlBuilder.append("&solYear=").append(solYear);
//                urlBuilder.append("&solMonth=").append();
            urlBuilder.append("&_type=").append("json");
            urlBuilder.append("&numOfRows=").append("80");
            
            log.info("Request URL : {}", urlBuilder.toString());

            connection = (HttpURLConnection) new URL(urlBuilder.toString()).openConnection();
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestMethod("GET");

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            responseTextBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null)
            {
                responseTextBuilder.append(line);
            }
            ObjectMapper mapper = new ObjectMapper();

            JsonParser parser;
            
            parser = mapper.createParser(responseTextBuilder.toString().replaceAll(" ", ""));
            astronomyDateResponse = parser.readValueAs(AstronomyDateResponse.class);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                reader.close();
                connection.disconnect();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        return astronomyDateResponse;
    }
    // 24절기 정보 조회
    public Astronomy24DateResponse get24DivisionsInfo(String solYear) throws RestClientException, URISyntaxException
    {
        BufferedReader                           reader = null;
        StringBuilder                        urlBuilder = null;
        StringBuilder               responseTextBuilder = null;
        HttpURLConnection                    connection = null;
        Astronomy24DateResponse astronomy24DateResponse = null;
        
        try
        {
            urlBuilder = new StringBuilder(baseURL + "get24DivisionsInfo");
            
            urlBuilder.append("?ServiceKey=").append(serviceKey);
            urlBuilder.append("&solYear=").append(solYear);
//                urlBuilder.append("&solMonth=").append();
            urlBuilder.append("&_type=").append("json");
            urlBuilder.append("&numOfRows=").append("80");
            
            log.info("Request URL : {}", urlBuilder.toString());
            
            connection = (HttpURLConnection) new URL(urlBuilder.toString()).openConnection();
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestMethod("GET");
            
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            responseTextBuilder = new StringBuilder();
            String line;
            
            while ((line = reader.readLine()) != null)
            {
                responseTextBuilder.append(line);
            }
            ObjectMapper mapper = new ObjectMapper();
            
            JsonParser parser;
            
            parser = mapper.createParser(responseTextBuilder.toString());
            astronomy24DateResponse = parser.readValueAs(Astronomy24DateResponse.class);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                reader.close();
                connection.disconnect();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        
        return astronomy24DateResponse;
    }
}

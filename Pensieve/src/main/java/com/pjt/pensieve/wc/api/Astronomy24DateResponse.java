package com.pjt.pensieve.wc.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Astronomy24DateResponse
{
    private String resultCode;
    
    private String resultMsg;
    
    private int pageNo;
    
    private int numOfRows;
    
    private int totalCount;
    
    private List<AstronomyDateItem> astronomyDateItems = new ArrayList<>();
    
    @JsonProperty("response")
    @SuppressWarnings("unchecked")
    public void unpackResponse(Map<String, Object> response) {
        Map<String, String>                        header = (Map<String, String>) response.get("header");
        Map<String, Object>                        body   = (Map<String, Object>) response.get("body");
        Map<String, ArrayList<Map<String, Object>>> items = (Map<String, ArrayList<Map<String, Object>>>) body.get("items");
        ArrayList<Map<String, Object>>               item = items.get("item");
        
        this.resultCode = header.get("resultCode");
        this.resultMsg  = header.get("resultMsg");
        this.pageNo     = (Integer) body.get("pageNo");
        this.numOfRows  = (Integer) body.get("numOfRows");
        this.totalCount = (Integer) body.get("totalCount"); 
        
        for (Map<String, Object> map : item) {
            AstronomyDateItem astronomyDateItem = new AstronomyDateItem();
            
            astronomyDateItem.setLocdate(Integer.parseInt(map.get("locdate").toString()));
            astronomyDateItem.setSunLongitude(Integer.parseInt(map.get("sunLongitude").toString()));
            astronomyDateItem.setSeq(Integer.parseInt(map.get("seq").toString()));
            astronomyDateItem.setKst(map.get("kst").toString().trim());
            astronomyDateItem.setDateKind((String) map.get("dateKind"));
            astronomyDateItem.setIsHoliday((String) map.get("isHoliday"));
            astronomyDateItem.setDateName((String) map.get("dateName"));
            
            astronomyDateItems.add(astronomyDateItem);
        }       
    }

}

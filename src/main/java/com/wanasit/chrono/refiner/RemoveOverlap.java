package com.wanasit.chrono.refiner;

import java.util.ArrayList;
import java.util.List;
import com.wanasit.chrono.ChronoOptions;
import com.wanasit.chrono.ParsedResult;
import com.wanasit.chrono.Refiner;

public class RemoveOverlap extends Refiner {
    
    @Override
    public List<ParsedResult> refine(List<ParsedResult> results, String text, ChronoOptions options) {
        
        if (results.size() < 2) return results;
        
        List<ParsedResult> filteredResults = new ArrayList<ParsedResult>();
        ParsedResult prevResult = results.get(0);
        
        for (int i=1; i<results.size(); i++){
            
            ParsedResult result = results.get(i);
            
            // If overlap, compare the length and discard the shorter one
            if (result.index < prevResult.index + prevResult.text.length()) {
                if (result.text.length() > prevResult.text.length()){
                    prevResult = result;
                }
                
            } else {
                filteredResults.add(prevResult);
                prevResult = result;
            }
        }
        
        // The last one
        if (prevResult != null) {
            filteredResults.add(prevResult);
        }
        
        return filteredResults;
    }
    
    
}

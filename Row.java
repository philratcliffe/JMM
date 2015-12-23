import java.util.ArrayList;
import java.util.List;

public class Row {
    
    private  List<Colour> guess; 
    private  List<IndicatorCode> indicator; 

    public Row(List<Colour> guess, List<IndicatorCode> indicator)
    {
        this.guess = guess;
        this.indicator = indicator;
    }

    public List<Colour> getGuess() {
        return guess;
    }

    public List<IndicatorCode> getIndicator() {
        return indicator;
    }

}

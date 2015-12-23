import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<Row> rows; 

    public Board()
    {
        this.rows = new ArrayList<>();
    }

    public void addRow(Row row)
    {
        rows.add(row);
    }


    public List<Row> getRows() 
    {
        return rows;
    }

    public int getRowCount()
    {
        return rows.size();
    }
     
}

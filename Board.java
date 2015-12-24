import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<Row> rows;
    private int width;

    public Board(int width)
    {
        this.rows = new ArrayList<>();
        this.width = width;
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

    public int getWidth()
    {
        return this.width;
    }
     
}

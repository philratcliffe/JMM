import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<Row> rows;
    private List<Colour> code;
    private int width;

    public Board(int width, List<Colour> code)
    {
        this.rows = new ArrayList<>();
        this.width = width;
        this.code = code;
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

    public List<Colour> getCode()
    {
        return this.code;
    }
     
}

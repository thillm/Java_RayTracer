import org.apache.commons.math3.linear.*;


public class MatrixText{
    
    public static void main(String[] args)
    {
        double[][] test = {{2,0,0},{0,2,0},{0,0,2}};
        RealMatrix matrix = new Array2DRowRealMatrix(test);
        RealVector vector = new ArrayRealVector(new double[]{1,2,3});
        System.out.println(matrix);
        System.out.println(vector);
        System.out.println(matrix.operate(vector));

    }
}

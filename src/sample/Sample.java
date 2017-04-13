package sample;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
 
public class Sample extends ApplicationFrame {
	//PieChart_AWT
   public Sample( String title ) {		//�����쿡 ���� �ٴ� ��. ��������.
      super( title ); 
      setContentPane(createDemoPanel( ));
   }
   
   private static PieDataset createDataset( ) {		//���� ������ ����Ÿ. ������ �ƴ�
      DefaultPieDataset dataset = new DefaultPieDataset( );
      dataset.setValue( "English" , new Double( 20 ) );  
      dataset.setValue( "Korean" , new Double( 20 ) );   
      dataset.setValue( "Math" , new Double( 60 ) );    
      return dataset;         
   }
   
   private static JFreeChart createChart( PieDataset dataset ) {
      JFreeChart chart = ChartFactory.createPieChart(      //��Ʈ ����. new�� �ƴ� chartFactory �޼��带 ���� �����Ѵ�.
         "SmartPhon sales Status",   // chart title 
         dataset,          // data    
         true,             // include legend   
         true, 
         false);

      return chart;
   }
   
   public static JPanel createDemoPanel( ) {
      JFreeChart chart = createChart(createDataset( ) );  
      return new ChartPanel( chart ); 
   }

   public static void main( String[ ] args ) {
      Sample demo = new Sample( "Mobile Sales" );  
      demo.setSize( 560 , 367 );    
      RefineryUtilities.centerFrameOnScreen( demo );    
      demo.setVisible( true ); 
   }
}
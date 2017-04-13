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
   public Sample( String title ) {		//윈도우에 제목 다는 것. 무시하자.
      super( title ); 
      setContentPane(createDemoPanel( ));
   }
   
   private static PieDataset createDataset( ) {		//실제 보여질 데이타. 디자인 아님
      DefaultPieDataset dataset = new DefaultPieDataset( );
      dataset.setValue( "English" , new Double( 20 ) );  
      dataset.setValue( "Korean" , new Double( 20 ) );   
      dataset.setValue( "Math" , new Double( 60 ) );    
      return dataset;         
   }
   
   private static JFreeChart createChart( PieDataset dataset ) {
      JFreeChart chart = ChartFactory.createPieChart(      //차트 생성. new가 아닌 chartFactory 메서드를 통해 생성한다.
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
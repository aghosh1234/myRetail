import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.retail.dao.service.RetailDaoService;
import com.retail.exception.RetailException;
import com.retail.service.RetailService;
import com.retail.util.ProductDTO;
import com.retail.util.RetailConstants;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RetailServiceTest {

	@InjectMocks
	private RetailService retailService;
	
	@Mock
	RetailDaoService retailDaoService;
	
	@Test
	public void getProductTest()  {
		ProductDTO productDTO = new ProductDTO();    			
		productDTO.setId(100);
		try {
			Mockito.when(retailDaoService.getProduct(Mockito.anyLong())).thenReturn(productDTO);
		} catch (RetailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		ProductDTO dto=null;
		try {
			dto = retailService.getProduct(100);
		} catch (RetailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		assertEquals(dto.getId(),productDTO.getId());		
	}
	
	@Test
	public void getProductTest_Exception_Test() throws RetailException  {
		ProductDTO productDTO = new ProductDTO();    			
		productDTO.setId(100);
		Mockito.when(retailDaoService.getProduct(Mockito.anyLong())).thenThrow(new RuntimeException());
		ProductDTO dto=null;
        try{
			dto = retailService.getProduct(100);
        }catch(Exception e){           	
        }
		assertNull(dto);		
	}
	
	@Test
	public void updateProduct_Test() throws RetailException{
		ProductDTO productDTO = new ProductDTO();    			
		productDTO.setId(100);
		productDTO.setName("Test");
		try{
			retailDaoService.saveProduct(productDTO);
		}catch(Exception e){           	
        }
		String result=null;
        try{
        	result = retailService.updateProduct(productDTO);
        }catch(Exception e){           	
        }
        assertNull(result);			
	}
}

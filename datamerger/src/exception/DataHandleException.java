package exception;

import java.sql.SQLException;

/**
 * 数据库操作异常类
 * @author cdh 2017-08-01
 *
 */
public class DataHandleException extends SQLException{

	private static final long serialVersionUID = 1L;

	public DataHandleException(String e){
		super(e);
	}
}

package com.paic.junit;

import com.paic.constant.Constant;
import com.paic.controller.CustController;
import com.paic.util.DaoUtil;
import oracle.jdbc.OracleTypes;
import org.junit.Assert;
import org.junit.Test;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustControllerTest {

    private CustController getController() {
        return new CustController();
    }

    @Test
    public void testWhiteService() {
        String resultCode = getController().whiteNameController();
        Assert.assertEquals(resultCode, Constant.SUCCESS);
    }

    @Test
    public void testOracleProcedure() {    //������Դ
        String sql = "{call queryempinfo(?,?,?,?)}";
        Connection connection = null;
        CallableStatement call = null;

        try {
            connection = DaoUtil.getConnection();
            call = connection.prepareCall(sql);

            //����in��������ֵ
            call.setInt(1, 7839);

            //����out����������
            call.registerOutParameter(2, OracleTypes.VARCHAR);
            call.registerOutParameter(3, OracleTypes.NUMBER);
            call.registerOutParameter(4, OracleTypes.VARCHAR);

            //ִ�е���
            call.execute();

            //ȡ�����
            String name = call.getString(2);
            double sal = call.getDouble(3);
            String job = call.getString(4);
            System.out.println(name + "\t" + sal + "\t" + job);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DaoUtil.release(connection, call, null, null);
        }
    }
    
    public static void main(String[] args) {
    	SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		System.out.println(format.format(new Date()));
	}

}

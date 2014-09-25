package net.oschina.app.bean;

public class User extends Base{
	public final static int RELATION_ACTION_DELETE = 0X00;
	public final static int RELATION_ACTION_ADD = 0x01;
	
	public final static int RELATION_TYPE_BOTH = 0x01;
    public final static int RELATION_TYPE_FANS_HIM = 0x02;
    public final static int RELATION_TYPE_NULL = 0x03;
    public final static int RELATION_TYPE_FANS_ME = 0x04;
    
    private int uid;
    private String location;
    private String name;
    private int followers;
    private int fans;
    private int scores;
    private String face;
    private String account;
	private String pwd;
	private Result validate;
	private boolean isRememberMe;
	
	private String jointime;
	private String gender;
	private String devplatform;
	private String expertise;
	private int relation;
	private String latestonline;
    
    
    
    
    
    
    
    
    
    
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

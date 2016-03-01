//分页处理组件

package util;

public class PageUtil {
	private int pageSize;//每页显示数
	private int recordCount;//总记录数
	private int currentPage;//当前页数
	private int pageCount;//总页数
	public PageUtil(int pageSize, int recordCount, int currentPage) {
		this.pageSize = pageSize;
		this.recordCount = recordCount;
		this.currentPage = currentPage;
	}
	
	public PageUtil(int pageSize, int recordCount){
		this(pageSize, recordCount, 1);
	}//当前页数默认为1
	
	//获得总页数
	public int getPageCount(){
		int size = recordCount / pageSize;//页数=总记录数/每页显示记录数
		int mod = recordCount % pageSize;
		if(mod != 0)//如果有余数，则页数+1
			size++;
		return recordCount == 0 ? 1 : size;//如果没有记录，则默认页数为1
	}
	//获得当前页数
	public int getCurrentPage() {
		return currentPage;
	}
	//设置当前页数
	public void setCurrentPage(int currentPage) {
		int vp = currentPage <= 0 ? 1 : currentPage;//如果当前页数<=0，设为1，否则为设置值
		vp = vp > getPageCount() ? getPageCount() : vp;//如果vp>总页数，则设置为总页数
		this.currentPage = vp;
	}
	//获得每页显示页数
	public int getPageSize() {
		return pageSize;
	}
	//设置每页显示页数
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	//获得记录总数
	public int getRecordCount() {
		return recordCount;
	}
	//设置记录总数
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
		
}
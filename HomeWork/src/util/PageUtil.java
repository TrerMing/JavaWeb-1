//��ҳ�������

package util;

public class PageUtil {
	private int pageSize;//ÿҳ��ʾ��
	private int recordCount;//�ܼ�¼��
	private int currentPage;//��ǰҳ��
	private int pageCount;//��ҳ��
	public PageUtil(int pageSize, int recordCount, int currentPage) {
		this.pageSize = pageSize;
		this.recordCount = recordCount;
		this.currentPage = currentPage;
	}
	
	public PageUtil(int pageSize, int recordCount){
		this(pageSize, recordCount, 1);
	}//��ǰҳ��Ĭ��Ϊ1
	
	//�����ҳ��
	public int getPageCount(){
		int size = recordCount / pageSize;//ҳ��=�ܼ�¼��/ÿҳ��ʾ��¼��
		int mod = recordCount % pageSize;
		if(mod != 0)//�������������ҳ��+1
			size++;
		return recordCount == 0 ? 1 : size;//���û�м�¼����Ĭ��ҳ��Ϊ1
	}
	//��õ�ǰҳ��
	public int getCurrentPage() {
		return currentPage;
	}
	//���õ�ǰҳ��
	public void setCurrentPage(int currentPage) {
		int vp = currentPage <= 0 ? 1 : currentPage;//�����ǰҳ��<=0����Ϊ1������Ϊ����ֵ
		vp = vp > getPageCount() ? getPageCount() : vp;//���vp>��ҳ����������Ϊ��ҳ��
		this.currentPage = vp;
	}
	//���ÿҳ��ʾҳ��
	public int getPageSize() {
		return pageSize;
	}
	//����ÿҳ��ʾҳ��
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	//��ü�¼����
	public int getRecordCount() {
		return recordCount;
	}
	//���ü�¼����
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
		
}
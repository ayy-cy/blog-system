import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * 博客数据访问层
 * 功能：模拟数据库CRUD、时间格式化、数据校验
 * 作者：张燕
 */
public class BlogDB {
    // 博客实体类（公开，解决访问权限报错）
    public static class Blog {
        private int id;
        private String title;
        private String content;
        private String createTime;

        public Blog(int id, String title, String content, String createTime) {
            this.id = id;
            this.title = title;
            this.content = content;
            this.createTime = createTime;
        }

        @Override
        public String toString() {
            return "ID:" + id + " | 标题:" + title + " | 时间:" + createTime + "\n内容:" + content;
        }

        public int getId() {
            return id;
        }
    }

    // 模拟数据库表
    private List<Blog> blogTable;
    private int nextId;
    private SimpleDateFormat sdf;

    // 构造方法：初始化数据库
    public BlogDB() {
        blogTable = new ArrayList<>();
        nextId = 1;
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("=== 数据库初始化完成 ===");
    }

    // 1. 新增博客
    public boolean addBlog(String title, String content) {
        if (title == null || title.trim().isEmpty()) {
            System.out.println("错误：标题不能为空！");
            return false;
        }
        if (content == null || content.trim().isEmpty()) {
            System.out.println("错误：内容不能为空！");
            return false;
        }
        String time = sdf.format(new Date());
        Blog blog = new Blog(nextId++, title, content, time);
        blogTable.add(blog);
        System.out.println("成功：博客新增完成！");
        return true;
    }

    // 2. 查询所有博客
    public void queryAll() {
        if (blogTable.isEmpty()) {
            System.out.println("提示：暂无博客数据");
            return;
        }
        System.out.println("\n===== 所有博客列表（共" + blogTable.size() + "条） =====");
        for (Blog blog : blogTable) {
            System.out.println(blog);
            System.out.println("----------------------------------------");
        }
    }

    // 3. 根据ID查询博客
    public Blog queryById(int id) {
        for (Blog blog : blogTable) {
            if (blog.getId() == id) {
                return blog;
            }
        }
        System.out.println("提示：未找到ID为" + id + "的博客");
        return null;
    }

    // 4. 删除博客
    public boolean deleteBlog(int id) {
        Blog target = queryById(id);
        if (target == null) {
            return false;
        }
        blogTable.remove(target);
        System.out.println("成功：博客删除完成！");
        return true;
    }

    // 主方法：测试运行
    public static void main(String[] args) {
        BlogDB db = new BlogDB();
        db.addBlog("项目管理实验", "完成实验3：质量计划与Git配置管理");
        db.addBlog("Java代码练习", "编写100行以上规范代码，满足实验要求");
        db.queryAll();
        db.deleteBlog(1);
        db.queryAll();
    }
}
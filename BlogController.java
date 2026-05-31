import java.util.Scanner;

/**
 * 博客系统控制层
 * 功能：接收用户输入、调用数据库、异常处理、菜单交互
 * 作者：邢明烁
 */
public class BlogController {
    private BlogDB blogDB;
    private Scanner scanner;

    // 构造方法：初始化依赖
    public BlogController() {
        blogDB = new BlogDB();
        scanner = new Scanner(System.in);
        System.out.println("===== 个人博客系统启动成功 =====");
    }

    // 显示主菜单
    public void showMenu() {
        System.out.println("\n========== 功能菜单 ==========");
        System.out.println("1. 新增博客");
        System.out.println("2. 查看所有博客");
        System.out.println("3. 根据ID查询博客");
        System.out.println("4. 删除博客");
        System.out.println("5. 退出系统");
        System.out.println("=================================");
    }

    // 新增博客逻辑
    public void addBlog() {
        System.out.print("请输入博客标题：");
        String title = scanner.nextLine();
        System.out.print("请输入博客内容：");
        String content = scanner.nextLine();
        blogDB.addBlog(title, content);
    }

    // 根据ID查询博客
    public void queryBlogById() {
        System.out.print("请输入要查询的博客ID：");
        int id = scanner.nextInt();
        scanner.nextLine(); // 吸收换行符
        BlogDB.Blog blog = blogDB.queryById(id);
        if (blog != null) {
            System.out.println("\n查询结果：\n" + blog);
        }
    }

    // 删除博客逻辑
    public void deleteBlog() {
        System.out.print("请输入要删除的博客ID：");
        int id = scanner.nextInt();
        scanner.nextLine();
        blogDB.deleteBlog(id);
    }

    // 启动系统
    public void start() {
        while (true) {
            showMenu();
            System.out.print("请输入功能编号（1-5）：");
            int choice;
            // 异常处理：防止非数字输入崩溃
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("错误：请输入有效数字！");
                continue;
            }
            // 菜单分支
            switch (choice) {
                case 1:
                    addBlog();
                    break;
                case 2:
                    blogDB.queryAll();
                    break;
                case 3:
                    queryBlogById();
                    break;
                case 4:
                    deleteBlog();
                    break;
                case 5:
                    System.out.println("系统退出，感谢使用！");
                    scanner.close();
                    return;
                default:
                    System.out.println("错误：请输入1-5之间的数字！");
            }
        }
    }

    // 主方法：运行系统
    public static void main(String[] args) {
        BlogController controller = new BlogController();
        controller.start();
    }
}
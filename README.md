# AIMS Project (Aims Store)

## Giới thiệu / Introduction

**AIMS** (viết tắt của **Aims Inventory Management System** hoặc **Aims Store**) là một hệ thống quản lý cửa hàng trực tuyến bán các sản phẩm media (sách, đĩa DVD, đĩa CD). Đây là một dự án Java được phát triển như một phần của khóa học Kỹ thuật Phần mềm tại Đại học Bách khoa Hà Nội (HUST).

**AIMS** (stands for **Aims Inventory Management System** or **Aims Store**) is an online store management system for selling media products (books, DVDs, CDs). This is a Java project developed as part of the Software Engineering course at Hanoi University of Science and Technology (HUST).

## Mô tả dự án / Project Description

Dự án AIMS cung cấp một giao diện dòng lệnh (CLI) để:
- Quản lý kho hàng (Store) với các sản phẩm media
- Quản lý giỏ hàng (Cart) của khách hàng
- Thực hiện các thao tác như thêm/xóa sản phẩm, tìm kiếm, sắp xếp, và phát media

The AIMS project provides a command-line interface (CLI) to:
- Manage the store inventory with media products
- Manage customer shopping cart
- Perform operations such as add/remove products, search, sort, and play media

## Cấu trúc dự án / Project Structure

```
AimsProject/
├── src/
│   └── hust/soict/dsai/aims/
│       ├── Aims.java                    # Main application class
│       ├── cart/
│       │   └── Cart.java                # Shopping cart management
│       ├── store/
│       │   └── Store.java               # Store inventory management
│       └── media/
│           ├── Media.java               # Abstract base class for all media
│           ├── Book.java                # Book media type
│           ├── Disc.java                # Base class for disc media
│           ├── DigitalVideoDisc.java    # DVD media type
│           ├── CompactDisc.java         # CD media type
│           ├── Track.java               # Track for CD
│           ├── Playable.java            # Interface for playable media
│           ├── MediaComparatorByCostTitle.java    # Comparator: Cost → Title
│           └── MediaComparatorByTitleCost.java    # Comparator: Title → Cost
├── Design/                              # Design documents
├── Requirement/                         # Requirement documents
└── AimsProject.iml                      # IntelliJ IDEA module file
```

## Các loại Media / Media Types

### 1. **Book** (Sách)
- Thuộc tính: title, category, cost, authors (danh sách tác giả)
- Không thể phát (not playable)

### 2. **DigitalVideoDisc (DVD)**
- Thuộc tính: title, category, cost, director, length
- Có thể phát (playable)

### 3. **CompactDisc (CD)**
- Thuộc tính: title, category, cost, artist, tracks (danh sách các bài hát)
- Có thể phát (playable)
- Mỗi CD chứa nhiều Track

## Chức năng chính / Main Features

### Menu chính / Main Menu
1. **View Store** - Xem danh sách sản phẩm trong cửa hàng
2. **Update Store** - Thêm/xóa sản phẩm khỏi cửa hàng
3. **See Current Cart** - Xem giỏ hàng hiện tại
0. **Exit** - Thoát chương trình

### Store Menu
1. **See a media's details** - Xem chi tiết sản phẩm
2. **Add a media to cart** - Thêm sản phẩm vào giỏ hàng
3. **Play a media** - Phát media (nếu có thể)
4. **See current cart** - Xem giỏ hàng

### Cart Menu
1. **Filter medias in cart** - Lọc sản phẩm theo ID hoặc Title
2. **Sort medias in cart** - Sắp xếp theo Cost→Title hoặc Title→Cost
3. **Remove media from cart** - Xóa sản phẩm khỏi giỏ hàng
4. **Play a media** - Phát media
5. **Place order** - Đặt hàng và xóa giỏ hàng

## Cách chạy dự án / How to Run

### Yêu cầu / Requirements
- Java Development Kit (JDK) 8 hoặc cao hơn

### Biên dịch / Compile
```bash
cd AimsProject
javac -d out src/hust/soict/dsai/aims/**/*.java src/hust/soict/dsai/aims/*.java
```

### Chạy / Run
```bash
java -cp out hust.soict.dsai.aims.Aims
```

## Tính năng kỹ thuật / Technical Features

### Object-Oriented Programming (OOP)
- **Kế thừa (Inheritance)**: Media → Book, Disc → DigitalVideoDisc, CompactDisc
- **Đóng gói (Encapsulation)**: Các thuộc tính private với getter/setter
- **Đa hình (Polymorphism)**: Interface Playable, phương thức toString()
- **Trừu tượng (Abstraction)**: Abstract class Media

### Design Patterns & Principles
- **Comparator Pattern**: MediaComparatorByCostTitle, MediaComparatorByTitleCost
- **Interface Segregation**: Playable interface
- **Single Responsibility**: Mỗi class có một trách nhiệm cụ thể

### Collections & Sorting
- Sử dụng ArrayList để quản lý danh sách media
- Hỗ trợ sắp xếp theo nhiều tiêu chí khác nhau
- Tìm kiếm và lọc dữ liệu

## Tài liệu tham khảo / Reference Documents

- **answer.txt**: Chứa các câu hỏi và câu trả lời về việc implement Comparable interface
- **Design/**: Thư mục chứa các tài liệu thiết kế hệ thống
- **Requirement/**: Thư mục chứa các tài liệu yêu cầu

## Tác giả / Author

- Đinh Tuấn Minh
- Mã sinh viên: 757052
- Khóa: 2024

## Học viện / Institution

Đại học Bách khoa Hà Nội (HUST) - Hanoi University of Science and Technology

---

**Lưu ý**: Đây là dự án học tập, được phát triển nhằm mục đích giáo dục và thực hành kỹ thuật lập trình hướng đối tượng.

**Note**: This is an educational project, developed for learning purposes and practicing object-oriented programming techniques.

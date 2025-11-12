#  SFWA.実習課題.A.01 — 備品管理システム（基本機能）

## フレームワーク応用実習 課題 A-01

### 概要
社内の備品を管理するシステムを開発する。  
データベースは **inventorydb** を使用。

ページ構成は全5ページ：

- ログインページ (login.html)  
- 備品リストページ (list.html)  
- 備品詳細ページ (detail.html)  
- 備品登録ページ (save.html)  
- 備品編集ページ (edit.html)

課題 A-01 では、ログインページを除く備品管理ページを完成させる。  

---

##  主要クラス・インターフェース構成

### Application.java
- Spring Boot起動クラス（自動生成）  
- パッケージ：`com.example.app`

### Location.java
- `locations` テーブルと連携する DTO  
- フィールド：
  - `private Integer id`
  - `private String name`
- 備考：Lombok利用可（getter/setter自動生成）

### Item.java
- `items` テーブルと連携する DTO  
- フィールド：
  - `private Integer id`
  - `private String name`
  - `private Integer amount`
  - `private Location location`
  - `private String note`
  - `private LocalDateTime registered`
  - `private LocalDateTime updated`
- バリデーションアノテーション使用可

### ItemMapper.java
- MyBatis マッパー  
- メソッド：
  - `List<Item> selectAll()` — 全件取得（登録日時の新しい順）  
  - `Item selectById(int id)` — ID指定で1件取得  
  - `void insert(Item item)` — 新規登録  
  - `void update(Item item)` — 更新  
  - `void delete(int id)` — 削除  
- XML: `/resources/mybatis/ItemMapper.xml`

### LocationMapper.java
- メソッド：`List<Location> selectAll()`（全場所取得）

### ItemService.java
- ビジネスロジック層のインターフェース  
- メソッド：
  - `List<Item> getAllItems()`
  - `Item getItemById(Integer id)`
  - `void addItem(Item item)`
  - `void editItem(Item item)`
  - `void deleteItem(Integer id)`
  - `List<Location> getItemLocations()`

### ItemServiceImpl.java
- `ItemService` の実装クラス  
- `ItemMapper`, `LocationMapper` を利用してDB操作を行う

### ItemController.java
- `/items` 配下のコントローラー  
- メソッド：
  - `showItems()` — `/items`（GET）: 一覧表示  
  - `showItemDetail()` — `/items/detail/{id}`（GET）: 詳細表示  
  - `showAddForm()` — `/items/add`（GET）: 登録フォーム表示  
  - `addItem()` — `/items/add`（POST）: 新規登録  
  - `showEditForm()` — `/items/edit/{id}`（GET）: 編集フォーム表示  
  - `editItem()` — `/items/edit/{id}`（POST）: 更新処理  
  - `deleteItem()` — `/items/delete/{id}`（GET）: 削除処理  

### ApplicationConfig.java
- 設定クラス。フィルターなどの登録用。

---

##  依存関係（pom.xml）

| カテゴリ | 依存関係 |
|-----------|-----------|
| Web | Spring Web |
| テンプレート | Thymeleaf |
| 開発ツール | DevTools, Lombok |
| 検証 | Validation |
| SQL | MyBatis Framework, MySQL Driver |

---

##  課題別仕様

###  A-01-1：備品リスト表示
- URL: `/items`
- 表示項目：ID / 品名 / 数量 / 場所
- 並び順：登録日時の新しい順
- 「備品管理システム」ロゴ → `/items`
- 「ログアウト」リンク → `/logout`
- 「備品の追加」ボタン → `/items/add`
- 各「詳細」ボタン → `/items/detail/{id}`

---

###  A-01-2：備品登録
- URL: `/items/add`
- メソッド：
  - GET：フォーム表示  
  - POST：登録処理  
- 入力項目：
  - 品名 / 数量 / 場所 / 備考  
- 「保存」押下：登録実行  
- 「キャンセル」押下：`/items` へ戻る  

**バリデーション仕様**

| 項目 | チェック内容 | エラーメッセージ |
|------|---------------|-------------------|
| 品名 | 未入力 | 備品名が未入力です |
| 品名 | 50文字以内 | 備品名は50文字以内で入力してください |
| 数量 | 未入力 | 数量が未入力です |
| 数量 | 整数チェック | 数量は整数で入力してください |
| 数量 | 0以上 | 数量は0以上の値を入力してください |

- 入力エラー時：再表示＋エラーメッセージ表示  
- 正常時：登録 → `/items` にリダイレクト

---

###  A-01-3：備品詳細表示
- URL: `/items/detail/{id}`
- 表示項目：
  - 備品名 / ID / 数量 / 場所 / 登録日時 / 更新日時 / 備考
- 日付フォーマット：  
  yyyy年MM月dd日(E) HH時mm分
- ボタンリンク：
  - 「備品リストに戻る」 → `/items`
  - 「編集」 → `/items/edit/{id}`
  - 「削除」 → `/items/delete/{id}`
- 該当データなし：  
  「お探しの備品は存在しません」

---

###  A-01-4：備品削除
- URL: `/items/delete/{id}`
- 「削除」ボタン押下時にJavaScriptで確認ダイアログ表示  
- 削除後：  
  「備品を削除しました」  
  （フラッシュメッセージ）

---

###  A-01-5：備品編集
- URL: `/items/edit/{id}`
- 編集フォームに既存データを表示  
- 「キャンセル」押下：詳細ページへ戻る  
- 保存時：登録時と同様のバリデーションを実行  
- 正常時：  
  「備品情報を更新しました」  
  詳細ページへリダイレクト  

---
#  SFWA.実習課題.A.02 — ログイン認証 

## フレームワーク応用実習 課題 A-02

### 概要
既存の備品管理システム（A-01）に対して、  
ログイン認証機能を追加実装する。

---

##  準備

### members テーブル作成
MySQL Workbenchで `inventorydb` に **members** テーブルを作成する。

| フィールド名 | 型 | PRIMARY KEY | NOT NULL | UNIQUE | AUTO_INCREMENT | 備考 |
|---------------|----|--------------|-----------|---------|----------------|------|
| id | INT | ✅ | ✅ | | ✅ | ID番号 |
| name | VARCHAR(20) | | ✅ | | | 名前 |
| login_id | VARCHAR(20) | | ✅ | ✅ | | ログインID |
| login_pass | CHAR(60) | | ✅ | | | ハッシュ済みパスワード |

### 初期データ例
| id | name | login_id | login_pass（BCryptハッシュ） |
|----|------|-----------|-------------------------------|
| 1 | 山田太郎 | taro | `$2a$10$WZHo5Z1FNVKo5rZcDdJ/seUemb1YC.T.z3AespUAwqB7P9/NGEUH.` |
| 2 | 鈴木花子 | hana | `$2a$10$vgBi0giJpGqLKq0QRetnJ.w/LDSqg3zLTXJzTlIa2c0Y/dmk.LZfjM` |

### pom.xml に jBCrypt 依存関係を追加
以下を追記して **BCrypt** を利用可能にする：

<dependency>  
　<groupId>de.svenkubiak</groupId>  
　<artifactId>jBCrypt</artifactId>  
　<version>0.4.3</version>  
</dependency>

---

##  実装クラス・構成

### Member.java
- DTO（members テーブルと連携）
- フィールド：
  - `private Integer id`
  - `private String name`
  - `private String loginId`
  - `private String loginPass`
- パッケージ：`com.example.app.domain`

---

### MemberMapper.java
- メソッド：`Member selectByLoginId(String loginId)`  
  （ログインIDで1件取得）
- XML: `/resources/mybatis/MemberMapper.xml`
- パッケージ：`com.example.app.mapper`

---

### MemberService.java
- メソッド：  
  `Member getAuthenticatedMember(String loginId, String loginPass)`
- 正常時：認証成功 → Memberを返す  
- 失敗時：`null` を返す  
- パッケージ：`com.example.app.service`

---

### MemberServiceImpl.java
- 上記サービスの実装クラス  
- `MemberMapper` 経由でDBアクセス  
- BCryptでパスワード照合  
- パッケージ：`com.example.app.service`

---

### AuthController.java
- ログイン・ログアウトを制御するコントローラー  
- パッケージ：`com.example.app.controller`
- メソッド：
  - **showLoginForm()**  
    GET `/`  
    → ログイン画面を表示  
  - **loginCheck()**  
    POST `/`  
    → ログイン検証、成功時はセッション保存＋`/items`へ  
  - **logout()**  
    GET `/logout`  
    → セッション削除、ログイン画面へリダイレクト  

---

### AuthFilter.java
- `jakarta.servlet.Filter` を実装  
- ログインチェック用フィルター  
- `/items/*` 配下のアクセス時、  
  セッションにユーザー情報がなければ `/login` にリダイレクト  
- パッケージ：`com.example.app.filter`

---

### ApplicationConfig.java
- フィルター登録クラス  
- `FilterRegistrationBean` を利用して `/items/*` に適用  
- パッケージ：`com.example.app.config`

---

##  ログイン画面仕様

- URL: `/`
- 入力項目：
  - ログインID
  - パスワード
- バリデーション：
  - ログインID未入力 → 「ログインIDが未入力です」  
  - パスワード未入力 → 「パスワードが未入力です」  
  - 不正な組み合わせ → 「ログインIDまたはパスワードが不正です」
- 成功時：
  - `/items` へ遷移  
  - フラッシュメッセージ「ログインしました」表示  
  - ナビバーにログインユーザー名を表示
- ログアウト：
  - `/logout` に遷移しセッション削除  
  - 「ログアウトしました」表示  



##  学習ポイントまとめ
- **Spring MVCのフロー**（Controller → Service → Mapper）を理解  
- **DTO / Entityの役割分離**  
- **MyBatisのXMLマッピング構造**  
- **ValidationアノテーションとBindingResultの利用**  
- **Flash属性によるユーザー通知**  
- **Thymeleafを用いたフォームバインディングとバリデーション表示**



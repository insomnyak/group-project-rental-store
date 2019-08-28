//package com.company.rentalstorerestwebservicegroupproject.dao;
//
//import com.company.rentalstorerestwebservicegroupproject.model.Item;
//import com.company.rentalstorerestwebservicegroupproject.model.Invoice;
//import com.company.rentalstorerestwebservicegroupproject.model.InvoiceItem;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.util.List;
//
//import com.company.rentalstorerestwebservicegroupproject.model.Customer;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNull;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
//public class InvoiceDaoTest {
//
//    @Autowired
//    CustomerDao customerDao;
//    @Autowired
//    InvoiceDao invoiceDao;
//    @Autowired
//    InvoiceItemDao invoiceItemDao;
//    @Autowired
//    ItemDao itemDao;
//
//    @Before
//    public void setUp() throws Exception {
//        // Clean up the test db
//        List<Customer> cList = customerDao.getAllCustomers();
//        for (Customer c : cList) {
//            customerDao.deleteCustomer(c.getcustomerId());
//        }
//
//        List<Invoice> iList = invoiceDao.getAllInvoices();
//
//        for (Invoice i : iList) {
//            invoiceDao.deleteInvoice(i.getinvoiceId());
//        }
//
//        List<InvoiceItem> inList = invoiceItemDao.getAllInvoiceItems();
//
//        for (InvoiceItem in : inList) {
//            invoiceItemDao.deleteInvoiceItem(in.getinvoiceItemId());
//        }
//
//        List<Item> itList = itemDao.getAllItems();
//
//        for (Item it : itList) {
//            itemDao.deleteItem(it.getitemId());
//        }
//
//    }
//
//    @Test
//    public void addGetDeleteCustomer() {
//
//        // Need to create a Label and an Artist first
//        Label label = new Label();
//        label.setName("A&M");
//        label.setWebsite("www.aandm.com");
//        label = labelDao.addLabel(label);
//
//        Artist artist = new Artist();
//        artist.setName("Rock Start");
//        artist.setInstagram("@RockStart");
//        artist.setTwitter("@TheRockStar");
//        artist = artistDao.addArtist(artist);
//
//        Album album = new Album();
//        album.setTitle("Greatest Hits");
//        album.setArtistId(artist.getId());
//        album.setLabelId(label.getId());
//        album.setReleaseDate(LocalDate.of(2010, 1, 5));
//        album.setListPrice(new BigDecimal("21.95"));
//
//        album = albumDao.addAlbum(album);
//
//        Album album1 = albumDao.getAlbum(album.getId());
//
//        assertEquals(album1, album);
//
//        albumDao.deleteAlbum(album.getId());
//
//        album1 = albumDao.getAlbum(album.getId());
//
//        assertNull(album1);
//
//    }
//
//    @Test(expected  = DataIntegrityViolationException.class)
//    public void addWithRefIntegrityException() {
//
//        Album album = new Album();
//        album.setTitle("Greatest Hits");
//        album.setArtistId(54);
//        album.setLabelId(91);
//        album.setReleaseDate(LocalDate.of(2010, 1, 5));
//        album.setListPrice(new BigDecimal("21.95"));
//
//        album = albumDao.addAlbum(album);
//
//    }
//
//    @Test
//    public void getAllAlbums() {
//
//        // Need to create a Label and an Artist first
//        Label label = new Label();
//        label.setName("A&M");
//        label.setWebsite("www.aandm.com");
//        label = labelDao.addLabel(label);
//
//        Artist artist = new Artist();
//        artist.setName("Rock Start");
//        artist.setInstagram("@RockStart");
//        artist.setTwitter("@TheRockStar");
//        artist = artistDao.addArtist(artist);
//
//        Album album = new Album();
//        album.setTitle("Greatest Hits");
//        album.setArtistId(artist.getId());
//        album.setLabelId(label.getId());
//        album.setReleaseDate(LocalDate.of(2010, 1, 5));
//        album.setListPrice(new BigDecimal("21.95"));
//
//        album = albumDao.addAlbum(album);
//
//        album = new Album();
//        album.setTitle("Leftovers");
//        album.setArtistId(artist.getId());
//        album.setLabelId(label.getId());
//        album.setReleaseDate(LocalDate.of(2012, 4, 5));
//        album.setListPrice(new BigDecimal("18.95"));
//
//        album = albumDao.addAlbum(album);
//
//        List<Album> aList = albumDao.getAllAlbums();
//
//        assertEquals(aList.size(), 2);
//
//    }
//
//    @Test
//    public void updateAlbum() {
//
//        Label label = new Label();
//        label.setName("A&M");
//        label.setWebsite("www.aandm.com");
//        label = labelDao.addLabel(label);
//
//        Artist artist = new Artist();
//        artist.setName("Rock Start");
//        artist.setInstagram("@RockStart");
//        artist.setTwitter("@TheRockStar");
//        artist = artistDao.addArtist(artist);
//
//        Album album = new Album();
//        album.setTitle("Greatest Hits");
//        album.setArtistId(artist.getId());
//        album.setLabelId(label.getId());
//        album.setReleaseDate(LocalDate.of(2010, 1, 1));
//        album.setListPrice(new BigDecimal("21.95"));
//
//        album = albumDao.addAlbum(album);
//
//        album.setTitle("NEW TITLE");
//        album.setReleaseDate(LocalDate.of(2000, 7, 7));
//        album.setListPrice(new BigDecimal("15.68"));
//
//        albumDao.updateAlbum(album);
//
//        Album album1 = albumDao.getAlbum(album.getId());
//        assertEquals(album1, album);
//
//    }
//
//
//
//
//}

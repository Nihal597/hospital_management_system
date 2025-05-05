package org.example.invoicegenerator.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.example.invoicegenerator.model.Item;
import org.example.invoicegenerator.model.Order;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    // This method generates an invoice for the given order
    @Override
    public byte[] generateInvoice(Order order) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);

        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
            // Set up basic layout parameters
            float margin = 50;
            float yStart = page.getMediaBox().getHeight() - margin;
            float yOffset = yStart;

            // Add a header with a larger font and underline
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 20);
            contentStream.beginText();
            contentStream.newLineAtOffset(margin, yOffset);
            contentStream.showText("Hospital Management System Invoice");
            contentStream.endText();
            yOffset -= 30;

            // Draw a line under the header
            contentStream.setLineWidth(1);
            contentStream.moveTo(margin, yOffset);
            contentStream.lineTo(page.getMediaBox().getWidth() - margin, yOffset);
            contentStream.stroke();
            yOffset -= 20;

            // Add customer details with a different font style
            contentStream.setFont(PDType1Font.HELVETICA_OBLIQUE, 14);
            contentStream.beginText();
            contentStream.newLineAtOffset(margin, yOffset);
            contentStream.showText("Customer Name: " + order.getCustomerName());
            contentStream.endText();
            yOffset -= 20;

            // Add table headers with a bold font
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
            contentStream.beginText();
            contentStream.newLineAtOffset(margin, yOffset);
            contentStream.showText("Lab Test Name");
            contentStream.newLineAtOffset(200, 0);
            contentStream.showText("No. of Tests");
            contentStream.newLineAtOffset(100, 0);
            contentStream.showText("Amount");
            contentStream.endText();
            yOffset -= 15;

            // Draw a line under the table headers
            contentStream.moveTo(margin, yOffset);
            contentStream.lineTo(page.getMediaBox().getWidth() - margin, yOffset);
            contentStream.stroke();
            yOffset -= 10;

            // Add the items with a regular font
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            for (Item item : order.getItems()) {
                contentStream.beginText();
                contentStream.newLineAtOffset(margin, yOffset);
                contentStream.showText(item.getName());
                contentStream.newLineAtOffset(200, 0);
                contentStream.showText(String.valueOf(item.getQuantity()));
                contentStream.newLineAtOffset(100, 0);
                contentStream.showText(item.getPrice() + " Rupees");
                contentStream.endText();
                yOffset -= 20;
            }

            // Add doctor appointment fee
            yOffset -= 10;
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(margin, yOffset);
            contentStream.showText("Doctor Appointment Fee: 1500 Rupees");
            contentStream.endText();
            yOffset -= 20;

            // Calculate and add total amount
            double totalAmount = calculateTotal(order.getItems()) + 1500; // Add the fixed appointment fee
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
            contentStream.beginText();
            contentStream.newLineAtOffset(margin, yOffset);
            contentStream.showText("Total Amount: RS " + totalAmount);
            contentStream.endText();
        }

        document.save(baos);
        document.close();
        return baos.toByteArray();
    }

    private double calculateTotal(List<Item> items) {
        double totalAmount = 0;
        for (Item item : items) {
            totalAmount += item.getQuantity() * item.getPrice();
        }
        return totalAmount;
    }
}

package task4;

public class CustomsDutyDecorator extends PricingDecorator {
    public CustomsDutyDecorator(PricingService wrapped) {
        super(wrapped);
    }

    @Override
    public double calculateTotal() {
        double subtotal = super.calculateTotal();
        return Math.round((subtotal * 1.085) * 100.0) / 100.0;
    }

    @Override
    public String getReceiptBreakdown() {
        return super.getReceiptBreakdown() + " + [Customs Duty: 8.5%]";
    }
}

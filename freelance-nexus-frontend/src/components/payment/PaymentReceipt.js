import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import paymentService from '../../services/paymentService';
 
const PaymentReceipt = () => {
  const { paymentId } = useParams();
  const [payment, setPayment] = useState(null);
 
  useEffect(() => {
    paymentService.getPaymentById(paymentId).then(setPayment);
  }, [paymentId]);
 
  if (!payment) return <div>Loading...</div>;
 
  return (
<div className="container mt-4">
<h3>Payment Receipt</h3>
<hr />
 
      <p><strong>Transaction ID:</strong> {payment.transactionId}</p>
<p><strong>Amount:</strong> ₹{payment.amount}</p>
<p><strong>Status:</strong> {payment.status}</p>
<p><strong>Date:</strong> {new Date(payment.paymentDate).toLocaleString()}</p>
<p><strong>Description:</strong> {payment.description}</p>
</div>
  );
};
export default PaymentReceipt;
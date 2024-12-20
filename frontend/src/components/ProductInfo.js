import React from 'react';

const ProductInfo = ({ product }) => {
  return (
    <div>
      {product ? (
        <div>
          <h2>{product.name}</h2>
          <p>Category: {product.category}</p>
          <p>Quantity: {product.quantity}</p>
        </div>
      ) : (
        <p>No product details available</p>
      )}
    </div>
  );
};

export default ProductInfo;

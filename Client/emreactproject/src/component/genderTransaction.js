import React, { useState, useEffect, useRef } from 'react';
import Chart from 'chart.js/auto';
import './TransactionCategory.css'; 

function GenderTransaction() {
    const [selectedState, setSelectedState] = useState('');
    const [categoryData, setCategoryData] = useState([]);
    const myChartRef = useRef(null);

    useEffect(() => {
        if (selectedState) {
            getCategoryData(selectedState);
        }
    }, [selectedState]);

    useEffect(() => {
        if (categoryData.length > 0) {
            renderPieChart();
        }
    }, [categoryData]);

    const handleStateChange = (event) => {
        setSelectedState(event.target.value);
    };

    const getCategoryData = (gender) => {
        const url = `http://localhost:8080/transactions/gendertransactions/${gender}`;
        console.log(url);
        fetch(url)
            .then(response => response.json())
            .then(data => {
                console.log(data);
                setCategoryData(data);
            })
            .catch(error => {
                console.error('Error fetching category data:', error);
            });
    };

    const renderPieChart = () => {
        if (categoryData.length > 0) {
            if (myChartRef.current) {
                myChartRef.current.destroy();
            }

            const labels = categoryData.map(itemData => itemData.category);
            const values = categoryData.map(itemData => itemData.spend);

            myChartRef.current = new Chart(document.getElementById("ordersChart"), {
                type: "bar",
                data: {
                    labels: labels,
                    datasets: [{
                        label: "Category Spending",
                        data: values,
                        backgroundColor: [
                            '#FF6384',
                            '#36A2EB',
                            '#FFCE56',
                            '#4BC0C0',
                            '#9966FF',
                            '#FF9900',
                            '#FF5733',
                            '#33FF99',
                            '#FF33CC',
                            '#FFCC33',
                            '#33CCFF',
                            '#FF3366',
                            '#66FF33',
                            '#FF6633',
                            '#33FF66'
                        ]
                    }]
                },options :{
                    indexAxis:'y'}
            });
        }
    };

    return (
        <div className="containers-data">
            <h2 className="page-title">Category of Transactions by Gender</h2>
            <label className="label"> Select from below : </label>
            <select className="state-dropdown" onChange={handleStateChange} value={selectedState}>
                <option value="">Select Gender</option>
                <option value="M">Male</option>
                <option value="F">Female</option>
                {/* Add more options as needed */}
            </select>
            <div className="chart-container">
    <canvas id="ordersChart" style={{ width: "800px", height: "400px" }}></canvas>
</div>
        </div>
    );
}

export default GenderTransaction;

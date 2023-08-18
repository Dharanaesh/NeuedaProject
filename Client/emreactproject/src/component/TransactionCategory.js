import React, { useState, useEffect, useRef } from 'react';
import Chart from 'chart.js/auto';
import './TransactionCategory.css'; 

function CategoryTransaction() {
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

    const getCategoryData = (state) => {
        const url = `http://localhost:8080/transactions/statetransactions/${state}`;
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
                type: "doughnut",
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
                }
            });
        }
    };

    return (
        <div className="containers-data">
            <h2 className="page-title">Transactions by State</h2>
            <label className="label"> Select any State : </label>
            <select className="state-dropdown" onChange={handleStateChange} value={selectedState}>
                <option value="">Select a state</option>
                <option value="NY">New York</option>
                <option value="CA">California</option>
                <option value="TX">Texas</option>
                {/* Add more options as needed */}
            </select>
            <div className="chart-container">
    <canvas id="ordersChart"></canvas>
</div>

        </div>
    );
}

export default CategoryTransaction;

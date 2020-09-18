INSERT INTO warehouse(description,location) VALUES ('Berlin piano','Berlin');
INSERT INTO warehouse(description,location) VALUES ('Chicago piano','Chicago');


INSERT INTO producer(company_name) VALUES ('Stainway&Sons');
INSERT INTO producer(company_name) VALUES ('Bechstein');
INSERT INTO producer(company_name) VALUES ('Bosendorf');


INSERT INTO dimension(height,length,width) VALUES (150,274,160);
INSERT INTO dimension(height,length,width) VALUES (151,227,151);
INSERT INTO dimension(height,length,width) VALUES (148,189,150);
INSERT INTO dimension(height,length,width) VALUES (148,155,150);

INSERT INTO bank_account(account_balance) VALUES (0);


INSERT INTO piano(sku,model_of_piano,name,price,weight,dimension_id,producer_id,warehouse_id) VALUES ('ASDFGHJK','GRAND_PIANO_S_155','home piano',150000,400, (SELECT dimension_id FROM dimension WHERE dimension_id=1),(SELECT producer_id FROM producer WHERE producer_id=1),(SELECT warehouse_id FROM warehouse WHERE warehouse_id=1));
INSERT INTO piano(sku,model_of_piano,name,price,weight,dimension_id,producer_id,warehouse_id) VALUES ('ASDFGHJK','GRAND_PIANO_S_155','home piano',150000,400, (SELECT dimension_id FROM dimension WHERE dimension_id=4),(SELECT producer_id FROM producer WHERE producer_id=1),(SELECT warehouse_id FROM warehouse WHERE warehouse_id=1));
INSERT INTO piano(sku,model_of_piano,name,price,weight,dimension_id,producer_id,warehouse_id) VALUES ('KSOEJFIEJ','GRAND_PIANO_C_227','home piano',300000,400, (SELECT dimension_id FROM dimension WHERE dimension_id=2),(SELECT producer_id FROM producer WHERE producer_id=1),(SELECT warehouse_id FROM warehouse WHERE warehouse_id=1));
INSERT INTO piano(sku,model_of_piano,name,price,weight,dimension_id,producer_id,warehouse_id) VALUES ('JAWEHISR','GRAND_PIANO_D_274','concert piano',699999,500, (SELECT dimension_id FROM dimension WHERE dimension_id=1),(SELECT producer_id FROM producer WHERE producer_id=1),(SELECT warehouse_id FROM warehouse WHERE warehouse_id=1));
INSERT INTO piano(sku,model_of_piano,name,price,weight,dimension_id,producer_id,warehouse_id) VALUES ('AJFOSJEF','GRAND_PIANO_B_211','home piano',400000,450, (SELECT dimension_id FROM dimension WHERE dimension_id=1),(SELECT producer_id FROM producer WHERE producer_id=1),(SELECT warehouse_id FROM warehouse WHERE warehouse_id=1));
INSERT INTO piano(sku,model_of_piano,name,price,weight,dimension_id,producer_id,warehouse_id) VALUES ('QWERTY','GRAND_PIANO_B_211','home piano',4000000,450, (SELECT dimension_id FROM dimension WHERE dimension_id=1),(SELECT producer_id FROM producer WHERE producer_id=1),(SELECT warehouse_id FROM warehouse WHERE warehouse_id=1));
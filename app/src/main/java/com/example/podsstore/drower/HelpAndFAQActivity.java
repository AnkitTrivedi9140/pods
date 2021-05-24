package com.example.podsstore.drower;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.podsstore.MainActivity;
import com.example.podsstore.R;

public class HelpAndFAQActivity extends AppCompatActivity {
TextView tvfaq1,tvfaq2,tvfaq3,tvfaq4,tvfaq5,tvfaq6,tvfaq7,tvfaq8,tvfaq9,tvfaq10,tvfaq11,tvfaq12,tvfaq13,tvfaq14,tvfaq15,tvfaq16,tvfaq17,tvfaq18,tvfaq19,tvfaq20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_and_f_a_q);
        tvfaq1=findViewById(R.id.tvfaq1);
        tvfaq2=findViewById(R.id.tvfaq2);
        tvfaq3=findViewById(R.id.tvfaq3);

        tvfaq4=findViewById(R.id.tvfaq4);
        tvfaq5=findViewById(R.id.tvfaq5);
        tvfaq6=findViewById(R.id.tvfaq6);
        tvfaq7=findViewById(R.id.tvfaq7);
        tvfaq8=findViewById(R.id.tvfaq8);
        tvfaq9=findViewById(R.id.tvfaq9);
        tvfaq10=findViewById(R.id.tvfaq10);
        tvfaq11=findViewById(R.id.tvfaq11);
        tvfaq12=findViewById(R.id.tvfaq12);
        tvfaq13=findViewById(R.id.tvfaq13);
        tvfaq14=findViewById(R.id.tvfaq14);
        tvfaq15=findViewById(R.id.tvfaq15);


        tvfaq16=findViewById(R.id.tvfaq16);
        tvfaq17=findViewById(R.id.tvfaq17);
        tvfaq18=findViewById(R.id.tvfaq18);
        tvfaq19=findViewById(R.id.tvfaq19);
        tvfaq20=findViewById(R.id.tvfaq20);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Help / FAQ");
        tvfaq1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), HelpActivity.class);
                intent.putExtra("question","How does Pods.Market work?");
                intent.putExtra("ans","Pods.Market is the biggest and most trusted medical wholesale B2B online platform. We connecting manufacturers and suppliers from all over the world with companies, organizations and governments who seek to buy high-quality products under the best terms and conditions. \n" +
                        "Pods.Market team putting a lot of effort into the securement of each transaction, starting from legal and financial verification of Sellers and Buyers to high-level protection for payment processing.\n" +
                        "When you become a registered Seller / Buyer on Pods.Market, you will have access to the largest healthcare marketplace available on the Internet. The list of products on Pods.Market is updated every day. If you are a Seller, you can list all the products you want to sell at a preferred price based on the quantity you choose. On the other hand, the Buyer can accept your offer or make a new one with a different price for the largest quantities of goods. The offer can be accepted or rejected. Pods.Market secure all payments, verify proof of product submissions and supervise deal from the first contact between Buyer/Seller to feedback from both sides after.  \n" +
                        "For more specific/unique requests that are not listed in the general catalog, we have a Special Request Board. How does it work? Anytime you have a request for a particular medical product and cannot find it in the general Pods.Market catalog, you have the option to post it on the Special Request Board and let registered (trusted) Sellers make an offer to you.\n" +
                        "The Pods.Market team is working daily to improve the user experience for all our customers. Please share your user experience with the Pods.Market team (link to contact form), we'd really appreciate your feedback.");
            startActivity(intent);
                finish();
            }
        });
        tvfaq2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), HelpActivity.class);
                intent.putExtra("question","How to register?");
                intent.putExtra("ans","\"To be able to buy or sell medical products on Pods.Market, you need to be a verified Buyer/Seller. To create a new Buyer or Seller account, select the \"\"Sing Up\"\" option in the upper right corner (for the desktop version) or Download the Pods.Market app from the online store according to your smartphone operating system.\n" +
                        "\n" +
                        "Fill in the registration form, which includes:\n" +
                        " - Company name\n" +
                        "- Valid Email address\n" +
                        "- Phone number\n" +
                        "Choose registration type for your company: \n" +
                        " - Buyer (SME/Corporate/Incorporate/Government) \n" +
                        " - Seller (Retailer/Allocation Holder/Distributor/Factory)\n" +
                        "\n" +
                        "Choose and Confirm your password. It must be at least 8 characters long and contain at least one number, one uppercase, and one lowercase letter.\n" +
                        "\n" +
                        "Read carefully \"\"Terms and Conditions\"\" and if you are agreeing to proceed confirm it by check the inbox \"\"I have read and accepted Terms and Conditions\"\". Check box for “I have read and accepted Terms and Conditions” \n" +
                        " Press \"\"Submit\"\"\n" +
                        "\n" +
                        "If you already have an account with Pods.Market use the \"\"Sign In\"\" option.\n" +
                        "Please, be aware, that each account at the Pods.Market requires a unique email address. It means that you can not register several accounts (Buyer or Seller) under the same email.\n" +
                        "\n" +
                        "After submission of the initial information for your account at Pods.Market you will receive a confirmation link (valid for 24 hours from the moment the email was delivered) at the address that you provided. Without confirmation our system will not allow you to move forward with our account verification process, that's why it is important to use a valid email to which you have full access.\n" +
                        "\n" +
                        "After your email address was successfully confirmed you have access to the verification procedure, which is mandatory for all Buyers and Sellers who choose a safe business environment at Pods.Market.\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\"\n");

                startActivity(intent);
                finish();
            }
        });
        tvfaq3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), HelpActivity.class);
                intent.putExtra("question","What is Pods.Market APP?");
                intent.putExtra("ans","\"\n" +
                        "Pods.Market APP is innovative solution for medical B2B market. It allows Sellers and Buyer’s from all over the world register, add products, buy products, control all requests, track orders, receive information about payments via cellphone or tablet. \n" +
                        "Here are 5 reasons, why it’s a good idea to use Pods.Market APP:\n" +
                        "1.        You have quick access to your Seller/Buyer account from your phone. \n" +
                        "2.        You can receive push notification about product listing approve, requests, payments, delivery and other events of your choosing, so you can respond to any events as fast as possible\n" +
                        "3.        You can add video and photo files for Prof of Product verification procedure or to product description page\n" +
                        "4.        Our APP focused on usability and user navigation – anyone can use it.\n" +
                        "5.        You can receive direct calls and messages from your Pods.Market manager\n" +
                        "Start sell and buy medical supplies with Pods.Market app today. Download it from … …. …. \n" +
                        "\"\n");

                startActivity(intent);
                finish();
            }
        });


        tvfaq4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), HelpActivity.class);

                intent.putExtra("question","How I can get Pods.Market APP on my phone?");
                intent.putExtra("ans","\"Search, get product details and specifications, read reviews, and purchase millions of medical supplies and equipment with the Pods Market app.\n" +
                        "Pods Market aap packs a variety of features and functions into this ample app, whether you’re buying or selling, reading reviews, checking specifications, or looking for the best solution and offer. \n" +
                        "Pods Market app on the App Store for iPhone and iPad and on Google Play for Androids.\n" +
                        "The developer, Pods Market, indicated that the app’s privacy practices may include handling of data as described below.\n" +
                        "The following data may be collected and linked to your identity:\n" +
                        "•        Purchases\n" +
                        "•        Financial Info\n" +
                        "•        Location\n" +
                        "•        Contact Info\n" +
                        "•        Contacts\n" +
                        "•        User Content\n" +
                        "•        Search History\n" +
                        "•        Identifiers\n" +
                        "•        Usage Data\n" +
                        "•        Sensitive Info\n" +
                        "•        Diagnostics\n" +
                        "•        Other Data\n" +
                        "\"\n");

                startActivity(intent);
                finish();
            }
        });
        tvfaq5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), HelpActivity.class);

                intent.putExtra("question","How to contact Pods.Market?");
                intent.putExtra("ans","To contact the managers of Pods.Market, please fill out the contact form or send us an email with your questions at info @ ... If you are registered as a Seller /Buyer, you can call your personal manager at the phone number that you will find in your account.\n");

                startActivity(intent);
                finish();
            }
        });
        tvfaq6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), HelpActivity.class);
                intent.putExtra("question","How can you be sure that the Seller is trustworthy?\n");
                intent.putExtra("ans","\"All Pods Market Sellers are required to submit all officially issued documents for legal and financial due diligence. After a successful verification procedure, Pods.Market managers contact the Seller's representatives directly through a video call in real-time or in person and once again confirming the address and identity. We also require all certifications for all products presented on our marketplace.\n" +
                        "In order to secure all deals, we supervise all payments and deal closure.\n" +
                        "If the Seller is suspected of any suspicious activities we block his account and enable transfers for future investigations.\"\n");


                startActivity(intent);
                finish();
            }
        });
        tvfaq7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), HelpActivity.class);
                intent.putExtra("question","What I can sell on Pods.Market?\n");
                intent.putExtra("ans","\"Pods.Market is the biggest medical B2B online marketplace. We take product safety and restrictions very seriously. Sellers should carefully review the Examples Permitted and Prohibited Listings* before listing a product. If you sell a Restricted Product, we may immediately suspend or terminate your selling privileges and block your account. The sale of illegal or unsafe products can also lead to legal action, including civil and criminal penalties. \n" +
                        "\n" +
                        "We are continuously innovating and working with regulators, third-party experts, suppliers, and sellers to improve how we detect and prevent illegal and unsafe products from entering our marketplace. Upon receiving a report of an issue, the Pods Market Product Validation Team will carefully review the report and take appropriate action.  \n" +
                        "\n" +
                        "If you wish to list items for international purchase, it is your responsibility to conduct proper research to ensure that the items listed comply with all applicable laws and regulations.\n" +
                        "\n" +
                        "*All examples provided on these help pages are not exhaustive and are provided solely as a reference guide.\"\n");

                startActivity(intent);
                finish();
            }
        });

        tvfaq8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), HelpActivity.class);
                intent.putExtra("question","I want to buy a product. How can I do it?\n");
                intent.putExtra("ans","To buy a product listed on Pods.Market you need to be registered and verified as a trusted buyer (link on the registration form). After your account was verified you can place an order on any item listed on our platform. Please, read the \"How to buy\" guide or schedule a one-on-one online Demo with our managers.  \n");

                startActivity(intent);
                finish();
            }
        });


        tvfaq9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), HelpActivity.class);
                intent.putExtra("question","When will my order be processed?\n");
                intent.putExtra("ans","We encourage Pods.Market Seller to proceed with requests as soon as possible, but no longer than 24 hours from the submission. The average time for a response from Sellers who operate in the same time zone as Buyer is around 2 to 4 hours. \n");

                startActivity(intent);
                finish();
            }
        });


        tvfaq10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), HelpActivity.class);
                intent.putExtra("question","What advertising opportunities are there for my company?\n");
                intent.putExtra("ans","Please, visit our Advertising for Business page  https://docs.google.com/spreadsheets/d/1nawcRw30tenCwxKkQ_h0nqn3Yp2W1zJu-LNiKeHRl-g/edit?usp=sharing or contact your personal manager for assistance\n");

                startActivity(intent);
                finish();
            }
        });

        tvfaq11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), HelpActivity.class);
                intent.putExtra("question","How can I track my order?\n");
                intent.putExtra("ans","After your order has been shipped, you will receive an email with the shipping details and tracking number.\n");
                startActivity(intent);
                finish();
            }
        });
        tvfaq12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), HelpActivity.class);
                intent.putExtra("question","What are verification documents?\n");
                intent.putExtra("ans","\"Verification documents accepted by Pods.Market are Officially issued documents, such as ID,  documents (please include a copy of the original documents and their official transcription if the main language of your documents other than English):\n" +
                        " - Person ID (Passport, first, second and last pages, Drivers License)\n" +
                        "- Business license, when applicable \n" +
                        "- Proof of address (utility bill, tenancy agreement, etc) \n" +
                        "- Finance details  (Bank statement, Proof-of-Founds)\n" +
                        "- Company registration certificate/form \n" +
                        "- Proof of Past Performance\n" +
                        "- DUNs reports\"\n\n");
                startActivity(intent);
                finish();
            }
        });
        tvfaq13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), HelpActivity.class);
                intent.putExtra("question","What should I do if the company refuses to exchange/return goods?\n");
                intent.putExtra("ans","Please contact our support team by email or phone. Include as many details as possible about the deal and your account. Please note that our support managers are required to verify your identity and that they will also contact the second party to the transaction/deal. The legal and financial team of Pods.Market works closely with each complaint and provides proactive support to resolve any issue in the shortest possible time and in the best possible way.\n");
                startActivity(intent);
                finish();
            }
        });
        tvfaq14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), HelpActivity.class);
                intent.putExtra("question","I am not satisfied with your service. How do I submit an improvement suggestion or complaint about a service?\n");
                intent.putExtra("ans","Please submit information via our Contact form or contact your personal manager directly\n");
                startActivity(intent);
                finish();
            }
        });
        tvfaq15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), HelpActivity.class);
                intent.putExtra("question","Why do we ask for a lot of documents when registering?\n");
                intent.putExtra("ans","Security and transparency are one of the main keys to all Pods.Market business processes. It is our legal and financial team's responsibility to ensure that all of our clients are can legitimate and financially trusted for any type and size of transactions that Pods.Market oversees.\n");
                startActivity(intent);
                finish();
            }
        });
        tvfaq16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), HelpActivity.class);
                intent.putExtra("question","Is it safe to store information about orders on Pods.Market?\n");
                intent.putExtra("ans","Yes. Safety and transparency are one of the main keys of all business process at Pods.Market. We guarantee, that all documentation, information about product requests, financial data, and personal information are kept under the most recent safety protocols.\n");
                startActivity(intent);
                finish();
            }
        });
        tvfaq17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), HelpActivity.class);
                intent.putExtra("question","How buyers will find my products?\n");
                intent.putExtra("ans","\"The master key to Buyer recognition is the proper name and description of your product. Please make sure there are no spelling errors in it. Include all product information: proper name, brand, photos, material, size chart, country of origin, certification, standardization, shelf life, industrial use, etc.\n" +
                        "Be sure to select the correct category and subcategory, if applicable. Products with the most complete and complete description will have priority in the ranking of the product list along with the personal rating of the Seller.\n" +
                        "For companies that offer exclusive products, or companies looking to promote new brands and products, we have several advertising options.\"\n");     startActivity(intent);
                finish();
            }
        });
        tvfaq18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), HelpActivity.class);
                intent.putExtra("question","I cannot enter the platform. What to do?\n");
                intent.putExtra("ans","Please, contact our support team by email or phone. Include as much as possible information about your account. Be aware, that our support team managers are obligated to ensure your identity and legal right to access an existing account with compleated verification at Pods.Market. We appreciate your patience and acceptance of our high standards of security procedures.\n");
                startActivity(intent);
                finish();
            }
        });
        tvfaq19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), HelpActivity.class);
                intent.putExtra("question","I do not remember the login and password to enter the site/APP. What to do?\n");
                intent.putExtra("ans","\"To find and reset your password:\n" +
                        "\n" +
                        "1. Go to the Forgot Password or Username page.\n" +
                        "\n" +
                        "2. Enter your email. Make sure the email you enter is the email you used to create the account. If any other email is used, an email will not be sent and you will be unable to reset the password. Click \"\"Reset password\"\"\n" +
                        "\n" +
                        "3. Check your email. After you enter your email an email will be sent to your Pods.Market-associated email address. Click Set a new password, and you will be redirected to a page where you can enter a new password.\n" +
                        "\n" +
                        "4. You will now be automatically logged-into Pods.Market with an updated password.\"\n");  startActivity(intent);
                finish();
            }
        });
        tvfaq20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), HelpActivity.class);
                intent.putExtra("question","I don't understand anything. What to do?\n");
                intent.putExtra("ans","Do not panic! We are here for you! Let's start with your guides - How to Sell and How to Buy. If you cannot find answers to your questions with them, we will be more than happy to help you! Just select a time (link to request a demo form) for our custom demo and our managers will provide complete training for you and your team.\n");  startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}